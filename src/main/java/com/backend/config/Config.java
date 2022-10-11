package com.backend.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties("spring.mvc.view")
public class Config {
    private String prefix;
    private String suffix;

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getPrefix(){
        return prefix;
    }

    public String getSuffix(){
        return suffix;
    }
}
