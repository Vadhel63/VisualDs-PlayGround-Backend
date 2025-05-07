package DsPlateform.Visualization.controller;

import DsPlateform.Visualization.entity.Code;
import DsPlateform.Visualization.entity.UserInfo;
import DsPlateform.Visualization.repository.UserInfoRepository;
import DsPlateform.Visualization.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Api")
public class CodeController {

    @Autowired
    private CodeService _codeService;

    @Autowired
    private UserInfoRepository userInfoRepository;
    @GetMapping("/codes")
    public ResponseEntity<List<Code>> GetAllCode() {
        return new ResponseEntity<>(_codeService.GetAllCode(), HttpStatus.OK);
    }
    @GetMapping("/codes/user")
    public ResponseEntity<List<Code>> getCodesForAuthenticatedUser(Authentication authentication) {
        String usernameOrEmail = authentication.getName(); // Comes from "sub" claim

        List<Code> userCodes = _codeService.getCodesByUsername(usernameOrEmail);
        return new ResponseEntity<>(userCodes, HttpStatus.OK);
    }

    @GetMapping("/codes/{id}")
    public ResponseEntity<Code> GetCodeById(@PathVariable int id) {
        Code _code = _codeService.GetCodeById(id);
        if (_code != null)
            return new ResponseEntity<>(_code, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PostMapping("/codes/user")
    public ResponseEntity<String> SaveCode(Authentication authentication, @RequestBody Code code) {
        String usernameOrEmail = authentication.getName(); // Extract email from JWT token

        boolean saved = _codeService.AddCodeByEmail(code, usernameOrEmail); // Pass email instead of userId
        if (saved)
            return new ResponseEntity<>("Code Saved Successfully", HttpStatus.CREATED);
        return new ResponseEntity<>("User Not Found", HttpStatus.NOT_FOUND);
    }

    @PostMapping("/codes/user/{userId}")
    public ResponseEntity<String> SaveCode(@PathVariable int userId, @RequestBody Code code) {
        boolean saved = _codeService.AddCode(code, userId);
        if (saved)
            return new ResponseEntity<>("Code Saved Successfully", HttpStatus.CREATED);
        return new ResponseEntity<>("User Not Found", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/codes/{id}")
    public ResponseEntity<Code> UpdateCode(@PathVariable int id, @RequestBody Code updatedCode) {
        Code c = _codeService.UpdateCode(id, updatedCode);
        if (c !=null)
            return new ResponseEntity<>(c, HttpStatus.OK);
        return new ResponseEntity<>(c, HttpStatus.NOT_FOUND);
    }


}
