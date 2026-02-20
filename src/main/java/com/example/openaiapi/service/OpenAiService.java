package com.example.openaiapi.service;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OpenAiService {

    private final RestTemplate restTemplate;

    @Value("${openai.api.url:https://api.openai.com/v1/responses}")
    private String apiUrl;

    @Value("${openai.model:gpt-4.1-mini}")
    private String model;

    @Value("${openai.api.key}")
    private String apiKey;

    public OpenAiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String ask(String question) {
        if (apiKey == null || apiKey.isBlank()) {
            throw new IllegalStateException("请先配置 OPENAI_API_KEY 环境变量");
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        Map<String, Object> requestBody = Map.of(
                "model", model,
                "input", List.of(Map.of("role", "user", "content", question))
        );

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);
        Map<?, ?> response = restTemplate.postForObject(apiUrl, entity, Map.class);

        if (response == null || !response.containsKey("output_text")) {
            throw new IllegalStateException("OpenAI 返回格式不符合预期");
        }

        Object outputText = response.get("output_text");
        if (!(outputText instanceof String text) || text.isBlank()) {
            throw new IllegalStateException("OpenAI 返回内容为空");
        }

        return text;
    }
}
