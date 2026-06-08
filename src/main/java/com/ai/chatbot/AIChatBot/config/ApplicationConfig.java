package com.ai.chatbot.AIChatBot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;

@Configuration
@Getter
public class ApplicationConfig {

    @Value("${openai.api-key}")
    private String apiKey;

    @Value("${openai.base-path}")
    private String basePath;

    @Value("${openai.model}")
    private String model;
    
}
