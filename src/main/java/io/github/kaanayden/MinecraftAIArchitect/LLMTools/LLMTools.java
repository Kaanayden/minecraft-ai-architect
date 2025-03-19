package io.github.kaanayden.MinecraftAIArchitect.LLMTools;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.kaanayden.MinecraftAIArchitect.LLMTools.ResponseTypes.ChatCompletion;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class LLMTools {

    private static final String API_URL = "https://api.openai.com/v1/chat/completions";

    private static String apiKey;

    public static void buildClient(String _apiKey) {
        apiKey = _apiKey;
    }

    public static String messageLLM(String message) {

        String requestBody = String.format("""
            {
                "model": "gpt-4o",
                "messages": [
                    {
                        "role": "user",
                        "content": "%s"
                    }
                ]
            }
        """, message.replace("\"", "\\\""));  // Escape quotes to prevent JSON syntax errors

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + apiKey)
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                // Convert JSON response to ChatCompletion object
                // Convert JSON response to ChatCompletion object
                ObjectMapper objectMapper = new ObjectMapper();
                ChatCompletion chatCompletion = objectMapper.readValue(response.body(), ChatCompletion.class);
                return chatCompletion.choices.getFirst().message.content;
            }

            throw new RuntimeException("LLM returned an error: " + response.statusCode() + " " + response.body());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
