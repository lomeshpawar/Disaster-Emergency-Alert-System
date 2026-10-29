package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.jupiter.api.Test;
import org.springframework.mock.env.MockEnvironment;

class DatabaseConfigurationTest {

    @Test
    void missingDatabaseCredentialsRemainRequiredPlaceholders() throws IOException {
        Properties properties = loadApplicationProperties();
        MockEnvironment environment = new MockEnvironment();

        String usernameExpression = properties.getProperty("spring.datasource.username");
        String passwordExpression = properties.getProperty("spring.datasource.password");

        assertNotNull(usernameExpression);
        assertNotNull(passwordExpression);
        assertThrows(
                IllegalArgumentException.class,
                () -> environment.resolveRequiredPlaceholders(usernameExpression));
        assertThrows(
                IllegalArgumentException.class,
                () -> environment.resolveRequiredPlaceholders(passwordExpression));
    }

    @Test
    void configuredDatabaseCredentialsResolveFromEnvironment() throws IOException {
        Properties properties = loadApplicationProperties();
        MockEnvironment environment = new MockEnvironment()
                .withProperty("DB_USERNAME", "test-user")
                .withProperty("DB_PASSWORD", "test-password");

        assertEquals(
                "test-user",
                environment.resolveRequiredPlaceholders(
                        properties.getProperty("spring.datasource.username")));
        assertEquals(
                "test-password",
                environment.resolveRequiredPlaceholders(
                        properties.getProperty("spring.datasource.password")));
    }

    private Properties loadApplicationProperties() throws IOException {
        Properties properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("application.properties")) {
            assertNotNull(input, "application.properties must be available on the test classpath");
            properties.load(input);
        }
        return properties;
    }
}
