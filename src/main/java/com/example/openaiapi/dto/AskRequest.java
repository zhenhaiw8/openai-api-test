package com.example.openaiapi.dto;

import javax.validation.constraints.NotBlank;

public class AskRequest {

    @NotBlank(message = "question 不能为空")
    private String question;

    public AskRequest() {
    }

    public AskRequest(String question) {
        this.question = question;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
