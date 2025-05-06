package DsPlateform.Visualization.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

@Service
public class OpenAIService {
    @Value("${openai.api.key}")
    private String openaiApiKey;

    private static final String OPENAI_API_URL = "https://api.openai.com/v1/completions";

    public String generateAlgorithmCode(String prompt) {
        RestTemplate restTemplate = new RestTemplate();

        // Set the headers for the API request
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + openaiApiKey);
        headers.set("Content-Type", "application/json");

        // Prepare the request body
        String body = "{\n" +
                "    \"model\": \"gpt-4\",\n" +
                "    \"prompt\": \"" + prompt + "\",\n" +
                "    \"max_tokens\": 150,\n" +
                "    \"temperature\": 0.7\n" +
                "}";

        // Send the request and get the response
        HttpEntity<String> entity = new HttpEntity<>(body, headers);
        ResponseEntity<String> response = restTemplate.exchange(OPENAI_API_URL, HttpMethod.POST, entity, String.class);

        // Extract the generated code from the response
        if (response.getStatusCode().is2xxSuccessful()) {
            String responseBody = response.getBody();
            // Extract the generated text (assuming response structure is correct)
            String code = extractCodeFromResponse(responseBody);
            return code;
        }

        return "Error generating code";
    }

    private String extractCodeFromResponse(String responseBody) {
        // Extract the code from the OpenAI API response
        // You might need to adjust this depending on the exact format of the response
        // A typical response looks like: {"choices":[{"text":"generated code here"}]}
        int start = responseBody.indexOf("\"text\":") + 8;
        int end = responseBody.indexOf("\"}", start);
        return responseBody.substring(start, end).trim();
    }

    }
