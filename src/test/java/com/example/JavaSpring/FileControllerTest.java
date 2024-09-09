package com.example.JavaSpring;

import com.example.JavaSpring.service.YandexObjectStorageService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class FileControllerTest {

    @Mock
    private YandexObjectStorageService storageService;

    @InjectMocks
    private com.example.JavaSpring.controller.FileController fileController;

    @Test
    public void testGetUploadUrl() {
        String fileName = "testfile.txt";
        String expectedUrl = "https://presigned-url.com/upload";
        when(storageService.generateUploadUrl(fileName)).thenReturn(expectedUrl);

        ResponseEntity<String> response = fileController.getUploadUrl(fileName);

        assertEquals(expectedUrl, response.getBody());
        verify(storageService, times(1)).generateUploadUrl(fileName);
    }

    @Test
    public void testGetDownloadUrl() {
        String fileName = "testfile.txt";
        String expectedUrl = "https://presigned-url.com/download";
        when(storageService.generateDownloadUrl(fileName)).thenReturn(expectedUrl);

        ResponseEntity<String> response = fileController.getDownloadUrl(fileName);

        assertEquals(expectedUrl, response.getBody());
        verify(storageService, times(1)).generateDownloadUrl(fileName);
    }
}
