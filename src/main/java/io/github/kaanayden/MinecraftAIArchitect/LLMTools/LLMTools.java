package io.github.kaanayden.MinecraftAIArchitect.LLMTools;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.github.kaanayden.MinecraftAIArchitect.LLMTools.ResponseTypes.ChatCompletion;
import io.github.kaanayden.MinecraftAIArchitect.Memory.ChatHistory;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;


public class LLMTools {

    private static final String API_URL = "https://api.openai.com/v1/chat/completions";

    private static String apiKey;

    public static void buildClient(String _apiKey) {
        apiKey = _apiKey;
    }

    private static ChatCompletion postMessage(String requestBody) {

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
                return objectMapper.readValue(response.body(), ChatCompletion.class);
            }

            throw new RuntimeException("LLM returned an error: " + response.statusCode() + " " + response.body());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public static String messageLLM(String message) {


        ObjectMapper objectMapper = new ObjectMapper();

        ObjectNode newMessageNode = objectMapper.createObjectNode();
        newMessageNode.put("role", ChatHistory.Role.user.toString());
        newMessageNode.put("content", message);

        ArrayNode messagesArray = objectMapper.createArrayNode();
        messagesArray.add(newMessageNode);

        // Create request body JSON
        ObjectNode requestBodyNode = objectMapper.createObjectNode();
        requestBodyNode.put("model", "gpt-4o");
        requestBodyNode.set("messages", messagesArray);

        try {
            String requestBody = objectMapper.writeValueAsString(requestBodyNode);

            // Send request and return response
            ChatCompletion chatCompletion = postMessage(requestBody);
            return chatCompletion.choices.getFirst().message.content;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public static String messageLLM(String message, ChatHistory chatHistory) {
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayNode messagesArray = objectMapper.createArrayNode();

        for (ChatHistory.ChatMessage chatMessage : chatHistory.getMessages()) {
            ObjectNode messageNode = objectMapper.createObjectNode();

            String role = chatMessage.getRole().toString();
            String currMessage = chatMessage.getMessage().replace("\"", "\\\""); // Escape quotes

            messageNode.put("role", role);
            messageNode.put("content", currMessage);

            messagesArray.add(messageNode);
        }

        ObjectNode newMessageNode = objectMapper.createObjectNode();
        newMessageNode.put("role", ChatHistory.Role.user.toString());
        newMessageNode.put("content", message);

        messagesArray.add(newMessageNode);

        // Create request body JSON
        ObjectNode requestBodyNode = objectMapper.createObjectNode();
        requestBodyNode.put("model", "gpt-4o");
        requestBodyNode.set("messages", messagesArray);

        try {
            String requestBody = objectMapper.writeValueAsString(requestBodyNode);

            // Send request and return response
            ChatCompletion chatCompletion = postMessage(requestBody);
            return chatCompletion.choices.getFirst().message.content;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
