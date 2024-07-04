package dev.ericcameron.model;

import com.fasterxml.jackson.databind.JsonNode;

public class OpenAiResponse {

    public boolean checkNode(JsonNode node){
        if(!node.isArray() || node.size() == 0){
            return false;
        }
        else{
            return true;
        }
    }

    public boolean checkUsername(JsonNode node) {
        JsonNode topNode = node.get(0);
        // The response from OpenAi will include a field named text and the value often end up with
        // Yes or Yes. as a response, preceded by a few carriage returns.
        String topText = topNode.get("text").asText().trim();

        if(topText.length() >= 3){
            return topText.substring(0,3).equals("Yes");
        }
        return false;
    }
}
