package io.github.kaanayden.MinecraftAIArchitect.LLMTools.ResponseTypes;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL) // Ignore null values in serialization
public class ChatCompletion {
    @JsonProperty("id")
    public String id;

    @JsonProperty("object")
    public String object;

    @JsonProperty("created")
    public long created;

    @JsonProperty("model")
    public String model;

    @JsonProperty("choices")
    public List<Choice> choices;

    @JsonProperty("usage")
    public Usage usage;

    @JsonProperty("service_tier")
    public String serviceTier;

    @JsonProperty("system_fingerprint")
    public String systemFingerprint;

    public static class Choice {
        @JsonProperty("index")
        public int index;

        @JsonProperty("message")
        public Message message;

        @JsonProperty("logprobs")
        public Object logprobs;

        @JsonProperty("finish_reason")
        public String finishReason;
    }

    public static class Message {
        @JsonProperty("role")
        public String role;

        @JsonProperty("content")
        public String content;

        @JsonProperty("refusal")
        public Object refusal;

        @JsonProperty("annotations")
        public List<Object> annotations;
    }

    public static class Usage {
        @JsonProperty("prompt_tokens")
        public int promptTokens;

        @JsonProperty("completion_tokens")
        public int completionTokens;

        @JsonProperty("total_tokens")
        public int totalTokens;

        @JsonProperty("prompt_tokens_details")
        public Map<String, Integer> promptTokensDetails;

        @JsonProperty("completion_tokens_details")
        public Map<String, Integer> completionTokensDetails;
    }
}
