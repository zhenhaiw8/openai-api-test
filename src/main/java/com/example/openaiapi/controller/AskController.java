package com.example.openaiapi.controller;

import com.example.openaiapi.dto.AskRequest;
import com.example.openaiapi.dto.AskResponse;
import com.example.openaiapi.service.OpenAiService;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AskController {

    private final OpenAiService openAiService;

    public AskController(OpenAiService openAiService) {
        this.openAiService = openAiService;
    }

    @PostMapping("/ask")
    public AskResponse ask(@Valid @RequestBody AskRequest request) {
        String answer = openAiService.ask(request.getQuestion());
        return new AskResponse(answer);
    }
}
