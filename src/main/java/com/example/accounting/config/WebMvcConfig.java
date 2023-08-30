package com.example.accounting.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private final StringToReasonConverter stringToReasonConverter;

    @Autowired
    public WebMvcConfig(StringToReasonConverter stringToReasonConverter) {
        this.stringToReasonConverter = stringToReasonConverter;
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(stringToReasonConverter);
    }
}

