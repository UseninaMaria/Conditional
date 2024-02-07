package com.example.springbootapp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;
@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringBootAppApplicationTests {
    @Autowired
    TestRestTemplate restTemplate;

    @Container
    private static final GenericContainer<?> myDevApp = new GenericContainer<>("devapp")
            .withExposedPorts(8080);
    @Container
    private static final GenericContainer<?> myProdApp = new GenericContainer<>("prodapp")
            .withExposedPorts(8081);

    @Test
    void testDevProfile() {
        ResponseEntity<String> forEntityDev = restTemplate.getForEntity(
                "http://localhost:" + myDevApp.getMappedPort(8080) + "/profile", String.class);
        assertEquals("Current profile is dev", forEntityDev.getBody());
    }

    @Test
    void testProdProfile() {
        ResponseEntity<String> forEntityProd = restTemplate.getForEntity(
                "http://localhost:" + myProdApp.getMappedPort(8081) + "/profile", String.class);
        assertEquals("Current profile is production", forEntityProd.getBody());
    }
}
