package com.ai.chatbot.AIChatBot.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.stereotype.Service;

import com.ai.chatbot.AIChatBot.config.ApplicationConfig;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AIService {

    private final ApplicationConfig config;

    public String askAI (String message) {
        
       try {
        HttpClient client = HttpClient.newHttpClient();

        String body = """
        {
            "model": "qwen3.6-27b",
            "messages": [
                {
                    "role": "user",
                    "content": "%s"
                }
            ]
        }
        """.formatted(message);

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(config.getBasePath()))
            .header("Content-Type", "application/json")
            .header("Authorization", "Bearer " + config.getApiKey())
            .POST(HttpRequest.BodyPublishers.ofString(body))
            .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        JsonNode root = new ObjectMapper().readTree(response.body());
        return root.path("choices").get(0).path("message").path("content").asText();

        } catch (Exception e) {
            return "Error processing the request: " + e.getMessage();
        }
    }
}
