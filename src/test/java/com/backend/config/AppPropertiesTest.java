package com.backend.config;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AppPropertiesTest {

    @Value("${spring.mvc.view.suffix}")
    private String suffix;

    @Test
    void parseYmlProperties() {
        Assertions.assertEquals(".html", suffix);
    }


}
