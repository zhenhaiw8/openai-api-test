package com.example.openaiapi.dto;

import jakarta.validation.constraints.NotBlank;

public record AskRequest(@NotBlank(message = "question 不能为空") String question) {
}
