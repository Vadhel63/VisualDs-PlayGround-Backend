package DsPlateform.Visualization.controller;
import DsPlateform.Visualization.service.OpenAIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.Map;
@RestController
@RequestMapping("/api/algorithm")

public class AlgorithmController {
    @Autowired
    private OpenAIService openAIService;

    @PostMapping("/generate")
    public String generateCode(@RequestBody Map<String, String> payload) {
        // Call the OpenAI service to generate the code
        String prompt = payload.get("prompt");
        System.out.println("Prompt: " + prompt);
        String generatedCode = openAIService.generateAlgorithmCode(prompt);

        return generatedCode;
    }



}
