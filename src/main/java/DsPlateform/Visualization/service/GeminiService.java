package DsPlateform.Visualization.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;

@Service
public class GeminiService {

    private final WebClient webClient;
    private final String apiKey;

    // Your full visualization library (can also be loaded from a file if needed)
    private final String libraryContext = """
        You are an assistant that generates JavaScript code to visualize algorithms using a custom visualization library.
        
        The library includes:
        - `vArray(inputArr, label)`: Initializes a visual array
        - `arr.get(index)`: Gets value at index
        - `arr.swap(i, j)`: Swaps elements visually
        - `arr.highlight([indices], color)`: Highlights indices with a color
        - `arr.unhighlight([indices])`: Removes highlight
        - `arr.getPointer(index, label)`: Returns a pointer object at index
        - Pointer methods: `increment()`, `isOutOfBound()`, `remove()`, `getIndex()`
        
        Please use these features strictly while generating code.
    """;

    public GeminiService(@Value("${gemini.api.key}") String apiKey) {
        this.apiKey = apiKey;
        this.webClient = WebClient.builder()
                .baseUrl("https://generativelanguage.googleapis.com")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    public String generateAlgorithmCode(String algorithmType) {
        try {
            // Combine library + algorithm-specific instruction
            String fullPrompt = libraryContext + "\nNow generate visualization code for the " + algorithmType + " algorithm.";

            // Build Gemini request body
            Map<String, Object> requestBody = new HashMap<>();
            Map<String, Object> content = new HashMap<>();
            List<Map<String, String>> parts = new ArrayList<>();

            Map<String, String> textPart = new HashMap<>();
            textPart.put("text", fullPrompt);
            parts.add(textPart);
            content.put("parts", parts);

            List<Map<String, Object>> contents = new ArrayList<>();
            contents.add(content);
            requestBody.put("contents", contents);

            Map<String, Object> generationConfig = new HashMap<>();
            generationConfig.put("temperature", 0.7);
            generationConfig.put("maxOutputTokens", 4096);
            requestBody.put("generationConfig", generationConfig);

            // Call Gemini API
            Map<String, Object> response = webClient.post()
                    .uri("/v1beta/models/gemini-2.0-flash:generateContent?key=" + apiKey)
                    .bodyValue(requestBody)
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block();

            if (response != null && response.containsKey("candidates")) {
                List<Map<String, Object>> candidates = (List<Map<String, Object>>) response.get("candidates");
                if (!candidates.isEmpty()) {
                    Map<String, Object> candidate = candidates.get(0);
                    Map<String, Object> candidateContent = (Map<String, Object>) candidate.get("content");
                    List<Map<String, Object>> candidateParts = (List<Map<String, Object>>) candidateContent.get("parts");

                    if (!candidateParts.isEmpty()) {
                        String text = (String) candidateParts.get(0).get("text");
                        return extractCodeBlock(text);
                    }
                }
            }

            return "Failed to generate algorithm code";

        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }

    private String extractCodeBlock(String text) {
        int startJs = text.indexOf("```javascript");
        if (startJs != -1) {
            int endJs = text.indexOf("```", startJs + 12);
            if (endJs != -1) {
                return text.substring(startJs + 13, endJs).trim();
            }
        }

        int start = text.indexOf("```");
        if (start != -1) {
            int end = text.indexOf("```", start + 3);
            if (end != -1) {
                return text.substring(start + 3, end).trim();
            }
        }

        return text;
    }
}

