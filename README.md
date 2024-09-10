# Описание проекта

Проект представляет собой API на базе Spring Boot для регистрации и авторизации пользователей с использованием email и пароля. Для авторизованных пользователей реализована возможность загрузки и скачивания файлов в/из облачного хранилища через AWS S3 или Yandex Object Storage. Данные пользователей хранятся в PostgreSQL. Для работы с базой данных используется Spring Data JDBC без ORM. Тестирование покрыто с использованием JUnit и Mockito.

## Требования:

- Java 17
- Maven 3.6+
- PostgreSQL
- Yandex Object Storage
- Git

## Шаги по развёртыванию:

### 1. Клонировать репозиторий:
```bash
git clone https://github.com/your-username/your-repository.git
cd your-repository
```

### 2. Установить зависимости:
```bash
mvn clean install
```

### 3. Настройка базы данных:

- Создать базу данных и пользователя:
```bash
sudo -u postgres psql
CREATE USER admin WITH PASSWORD 'admin';
CREATE DATABASE yourdb;
GRANT ALL PRIVILEGES ON DATABASE yourdb TO admin;
```

- Настроить `application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/yourdb
spring.datasource.username=admin
spring.datasource.password=admin
```

### 4. Настройка Yandex Object Storage:

- Создать бакет

- Настроить `application.properties`:

#### Yandex Object Storage:
```properties
cloud.yandex.s3.access-key=your-access-key
cloud.yandex.s3.secret-key=your-secret-key
cloud.yandex.s3.endpoint=https://storage.yandexcloud.net
cloud.yandex.s3.bucket=your-bucket-name
cloud.yandex.s3.region=ru-central1
```

### 5. Запуск приложения:
```bash
mvn spring-boot:run
```

Приложение будет доступно по адресу: [http://localhost:8080](http://localhost:8080)

### 6. Тестирование:
```bash
mvn test
```
