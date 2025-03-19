package io.github.kaanayden.MinecraftAIArchitect.LLMTools;
import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.ChatCompletion;
import com.openai.models.ChatCompletionCreateParams;
import com.openai.models.ChatModel;


public class LLMTools {

    private static OpenAIClient client;

    public static void buildClient(String apiKey) {
        client = OpenAIOkHttpClient.builder().apiKey(apiKey).build();
    }

    public static String messageLLM(String message) {

        ChatCompletionCreateParams params = ChatCompletionCreateParams.builder()
                .addUserMessage(message)
                .model(ChatModel.CHATGPT_4O_LATEST)
                .build();

        ChatCompletion chatCompletion = client.chat().completions().create(params);

        return chatCompletion.choices().getFirst().message().content().orElse( "No response from LLM");
    }

}
