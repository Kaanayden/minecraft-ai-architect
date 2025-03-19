package io.github.kaanayden.MinecraftAIArchitect;

import io.github.kaanayden.MinecraftAIArchitect.LLMTools.LLMTools;

public class Test {
    public static void main(String[] args) {
        // Add OPENAI_API_KEY as env var
        String response = LLMTools.messageLLM("Hi! How are you?");
        System.out.println(response);

    }
}
