package com.example.demoportflio;

import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;


@ActiveProfiles("test")
@SpringBootTest(
        ,
        classes = DemoportflioApplication.class)
class DemoportflioApplicationTests {

    @Test
    void contextLoads() {
    }

}
