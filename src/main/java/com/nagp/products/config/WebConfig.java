package com.nagp.products.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class WebConfig {

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("http://13.235.208.227:80");
        corsConfiguration.addAllowedOrigin("http://13.235.208.227");
        corsConfiguration.addAllowedOrigin("https://13.235.208.227");
        corsConfiguration.addAllowedOrigin("http://localhost:3000");
        corsConfiguration.addAllowedOrigin("http://localhost:3001");
        corsConfiguration.addAllowedOrigin("http://localhost:8080");
        corsConfiguration.addAllowedOrigin("http://13.235.208.227:8089");
        corsConfiguration.addAllowedOrigin("https://13.235.208.227:8089");
        corsConfiguration.addAllowedHeader("*"); // Allow all headers
        corsConfiguration.addAllowedMethod("*"); // Allow all HTTP methods (GET, POST, PUT, DELETE, etc.)
        corsConfiguration.setAllowCredentials(true); // Allow cookies if needed

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration); // Apply to all endpoints
        return new CorsFilter(source);
    }
}

