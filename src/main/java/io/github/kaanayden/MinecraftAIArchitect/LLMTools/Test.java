package io.github.kaanayden.MinecraftAIArchitect.LLMTools;

public class Test {
    public static void main(String[] args) {
        // Add OPENAI_API_KEY as env var
        LLMTools.buildClient(System.getenv("OPENAI_API_KEY"));
        String response = LLMTools.messageLLM("Hi! How are you?");
        System.out.println(response);

    }
}
