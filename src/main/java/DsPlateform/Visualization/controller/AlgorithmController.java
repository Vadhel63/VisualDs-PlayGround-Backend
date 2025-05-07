package DsPlateform.Visualization.controller;
//import com.yourproject.service.GeminiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import DsPlateform.Visualization.service.GeminiService;

import java.util.Map;

@RestController
@RequestMapping("/api/algorithm")
@CrossOrigin(origins = "*") // Added for convenience when testing with frontend
public class AlgorithmController {
  
  private final GeminiService geminiService;
  
  public AlgorithmController(GeminiService geminiService) {
      this.geminiService = geminiService;
  }
  
  @GetMapping("/generate")
  public ResponseEntity<String> generateAlgorithmGet(@RequestParam String type) {
      String generatedCode = geminiService.generateAlgorithmCode(type);
      return ResponseEntity.ok(generatedCode);
  }
  
  @PostMapping("/generate")
  public ResponseEntity<String> generateAlgorithmPost(@RequestBody Map<String, String> request) {
      String type = request.get("type");
      if (type == null || type.isEmpty()) {
          return ResponseEntity.badRequest().body("Algorithm type is required");
      }
      String generatedCode = geminiService.generateAlgorithmCode(type);
      return ResponseEntity.ok(generatedCode);
  }
}
