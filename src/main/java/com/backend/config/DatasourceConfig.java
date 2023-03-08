package com.backend.config;

import javax.sql.DataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Configuration
@Component
@EnableConfigurationProperties
public class DatasourceConfig {
    private final String url = "jdbc:postgresql://localhost:5432/spring_shop";
    //private String url = "jdbc:postgresql://192.168.0.107/spring_shop";

    private final String username = "postgres";
    private final String password = "pswd";

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource getDataSource(){
        return DataSourceBuilder
                .create()
                .url(url)
                .username(username)
                .password(password)
                .build();
    }


}
