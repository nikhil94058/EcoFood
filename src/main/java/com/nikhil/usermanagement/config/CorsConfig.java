package com.nikhil.usermanagement.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")                        // Apply to all endpoints
                .allowedOriginPatterns("*")               // Allow all origins (works for localhost, Live Server, file://)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allow all HTTP methods
                .allowedHeaders("*")                       // Allow all headers
                .allowCredentials(false);                  // No cookies needed
    }
}
