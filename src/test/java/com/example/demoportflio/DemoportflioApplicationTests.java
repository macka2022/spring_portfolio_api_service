package com.example.demoportflio;

import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.TestPropertySource;

//@ActiveProfiles("test")
@ActiveProfiles("test")  // ← Utilisez cette annotation à la place
@SpringBootTest
class DemoportflioApplicationTests {

    @Test
    void contextLoads() {
    }

}
