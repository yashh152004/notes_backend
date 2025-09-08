package com.example.backend_notes.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class hj {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**")
                    .allowedOrigins("http://localhost:3000", "http://10.238.100.168:3000") // ✅ Added localhost
                    .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")   // ✅ Added PATCH + OPTIONS
                    .allowedHeaders("*")
                    .allowCredentials(true);
            }
        };
    }
}
