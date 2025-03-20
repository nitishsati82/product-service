package com.nagp.products.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "open-search-config")
@Data
public class OpenSearchFieldConfig {
    private String host;
    private String user;
    private String password;
}