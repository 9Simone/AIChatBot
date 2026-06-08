package com.ai.chatbot.AIChatBot.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ai.chatbot.AIChatBot.model.Message;
import com.ai.chatbot.AIChatBot.service.AIService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatController {

    private final AIService aiService;
    
    @PostMapping
    public String askAI(@RequestBody Message message) {
        return aiService.askAI(message.getMessage());
    }
}
