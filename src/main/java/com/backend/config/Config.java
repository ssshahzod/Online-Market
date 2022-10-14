package com.backend.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties("spring.mvc.view")
public class Config {
    private String prefix;
    private String suffix;

    Logger logger = LoggerFactory.getLogger(Config.class);

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getPrefix(){
        logger.warn("Returning prefix value: {}", prefix);
        return prefix;
    }

    public String getSuffix(){
        return suffix;
    }
}
