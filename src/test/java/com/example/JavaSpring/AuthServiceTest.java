package com.example.JavaSpring;

import com.example.JavaSpring.model.User;
import com.example.JavaSpring.repository.UserRepository;
import com.example.JavaSpring.service.AuthService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AuthService authService;

    @Test
    public void testRegister() {
        String email = "test@example.com";
        String password = "password123";
        authService.register(email, password);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void testAuthenticateSuccess() throws Exception {
        String email = "test@example.com";
        String password = "password123";
        User user = new User(1L, email, new BCryptPasswordEncoder().encode(password));

        when(userRepository.findByEmail(email)).thenReturn(user);

        User result = authService.authenticate(email, password);
        assertNotNull(result);
    }

    @Test
    public void testAuthenticateFail() {
        String email = "test@example.com";
        String password = "wrongpassword";
        when(userRepository.findByEmail(email)).thenReturn(null);

        assertThrows(Exception.class, () -> authService.authenticate(email, password));
    }
}
