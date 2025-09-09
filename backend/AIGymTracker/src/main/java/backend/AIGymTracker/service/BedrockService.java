package backend.AIGymTracker.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.bedrockruntime.BedrockRuntimeClient;
import software.amazon.awssdk.services.bedrockruntime.model.InvokeModelRequest;
import software.amazon.awssdk.services.bedrockruntime.model.InvokeModelResponse;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BedrockService {
    
    private final BedrockRuntimeClient bedrockRuntimeClient;
    private final ObjectMapper objectMapper;
    
    private static final String CLAUDE_HAIKU_MODEL_ID = "anthropic.claude-3-haiku-20240307-v1:0";
    
    public String generateWorkoutFeedback(String prompt) {
        try {
            // Use Jackson to properly serialize JSON
            var requestPayload = Map.of(
                "anthropic_version", "bedrock-2023-05-31",
                "max_tokens", 1000,
                "messages", List.of(
                    Map.of(
                        "role", "user",
                        "content", prompt
                    )
                )
            );
            
            String requestBody = objectMapper.writeValueAsString(requestPayload);
            
            InvokeModelRequest request = InvokeModelRequest.builder()
                .modelId(CLAUDE_HAIKU_MODEL_ID)
                .body(SdkBytes.fromUtf8String(requestBody))
                .build();
            
            InvokeModelResponse response = bedrockRuntimeClient.invokeModel(request);
            String responseBody = response.body().asUtf8String();
            
            JsonNode jsonResponse = objectMapper.readTree(responseBody);
            return jsonResponse.get("content").get(0).get("text").asText();
            
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate workout feedback: " + e.getMessage(), e);
        }
    }
}