package com.ba.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@ConfigurationProperties("spring.datasource")
@SuppressWarnings("unused")
public class DBConfiguration {

    @Profile("test")
    @Bean
    public String h2DatabaseConnection(){
        System.out.println("DB Connection for TEST - H2");
        return "DB Connection for TEST - H2";
    }

    @Profile("prod")
    @Bean
    public String mySqlDatabaseConnection(){
        System.out.println("DB Connection for PROD - MySql");
        return "DB Connection for PROD - MySql";
    }
}
