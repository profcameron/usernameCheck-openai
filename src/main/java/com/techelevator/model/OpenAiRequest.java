package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OpenAiRequest {
    private String prompt;
    @JsonProperty("max_tokens")
    private int maxTokens;

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String userName) {
        // Had to be very explicit to try to get a yes or no answer here.
        this.prompt = prompt = "Could the username '" + userName + "' be considered offensive, or does it contain any offensive words? Answer only yes or no.";
    }

    public int getMaxTokens() {
        return maxTokens;
    }

    public void setMaxTokens(int maxTokens) {
        this.maxTokens = maxTokens;
    }
}
