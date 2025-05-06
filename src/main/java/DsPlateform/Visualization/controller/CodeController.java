package DsPlateform.Visualization.controller;

import DsPlateform.Visualization.entity.Code;
import DsPlateform.Visualization.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Api")
public class CodeController {

    @Autowired
    private CodeService _codeService;

    @GetMapping("/codes")
    public ResponseEntity<List<Code>> GetAllCode() {
        return new ResponseEntity<>(_codeService.GetAllCode(), HttpStatus.OK);
    }

    @GetMapping("/codes/{id}")
    public ResponseEntity<Code> GetCodeById(@PathVariable int id) {
        Code _code = _codeService.GetCodeById(id);
        if (_code != null)
            return new ResponseEntity<>(_code, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/codes/user/{userId}")
    public ResponseEntity<String> SaveCode(@PathVariable int userId, @RequestBody Code code) {
        boolean saved = _codeService.AddCode(code, userId);
        if (saved)
            return new ResponseEntity<>("Code Saved Successfully", HttpStatus.CREATED);
        return new ResponseEntity<>("User Not Found", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/codes/{id}")
    public ResponseEntity<String> UpdateCode(@PathVariable int id, @RequestBody Code updatedCode) {
        boolean updated = _codeService.UpdateCode(id, updatedCode);
        if (updated)
            return new ResponseEntity<>("Code Updated Successfully", HttpStatus.OK);
        return new ResponseEntity<>("Code with Specific ID Not Found", HttpStatus.NOT_FOUND);
    }
}
