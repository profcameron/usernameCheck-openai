package dev.ericcameron;

import com.fasterxml.jackson.databind.JsonNode;
import dev.ericcameron.dao.OpenAiConfiguration;
import dev.ericcameron.model.OpenAiRequest;
import dev.ericcameron.model.OpenAiResponse;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.util.Scanner;

public class OpenAIMain {

    public static void main(String[] args) {

        RestTemplate restTemplate = new RestTemplate();
        String apiKey = System.getenv("OpenAiApiKey");
        OpenAiRequest postData = new OpenAiRequest();
        OpenAiResponse response = new OpenAiResponse();

        if (apiKey == null) {
            System.out.println("This requires you to set a Windows environment variable named OpenAiApiKey.");
            System.out.println("You can set this in Windows by typing Environment Variables from the Start Menu,");
            System.out.println("clicking Environment Variables, and clicking New.");
            System.out.println("You may need to restart your IntelliJ after setting the variable.");
            System.out.println("The key can be acquired through OpenAI.");
        } else {
            OpenAiConfiguration config = new OpenAiConfiguration(apiKey);

            System.out.print("Please enter a username to check: ");

            Scanner input = new Scanner(System.in);

            // Get username from user, trim it.
            String userName = input.nextLine().trim();

            // Going to set the prompt to ask if the username is appropriate
            postData.setPrompt(userName);

            // Limit processing power!
            int maxTokens = 100;

            // OpenAiRequest is the model for the request - in this case we only send the prompt and max_tokens
            postData.setMaxTokens(maxTokens);

            // Finalize request with the post data and the headers
            HttpEntity<OpenAiRequest> entity = new HttpEntity<>(postData, config.getHeaders());

            // Post to the OpenAI endpoint, get a String result
            try{
                JsonNode result = restTemplate.postForObject(config.getApiEndpoint(), entity, JsonNode.class);
                try {
                    JsonNode choicesNode = result.get("choices");
                    if(response.checkNode(choicesNode)){
                        if (response.checkUsername(choicesNode)) {
                            System.out.println("The username " + userName + " is NOT okay.");
                        } else {
                            System.out.println("The username " + userName + " is okay.");
                        }
                    } else {
                        System.out.println("Error: No choices found in the JSON response.");
                    }
                } catch (Exception e) {
                    System.out.println("RestTemplate failure");
                    System.out.println(e.getCause());
                }
            }catch (RestClientResponseException e){
                System.out.println("RestClientResponseException");
                System.out.println("Error code: " + e.getRawStatusCode() + " " + e.getStatusText());
            }catch (ResourceAccessException e){
                System.out.println("ResourceAccessException");
                System.out.println(e.getMessage());
            }
        }
    }
}