package DsPlateform.Visualization.controller;

import DsPlateform.Visualization.entity.UserInfo;
import DsPlateform.Visualization.repository.UserInfoRepository;
import DsPlateform.Visualization.service.TokenService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserInfoRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AuthController(UserInfoRepository userRepository, PasswordEncoder passwordEncoder,
                          AuthenticationManager authenticationManager, TokenService tokenService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping("/signup")
    public String signup(@RequestBody UserInfo user) {
        // Check if the email already exists in the database
        if (userRepository.existsByEmail(user.getEmail())) {
            return "Email is already registered!";
        }

        // If the email doesn't exist, save the user
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Encrypt the password
        userRepository.save(user); // Save the new user to the database
        return "User registered successfully!";
    }

    @PostMapping("/login")
    public String login(@RequestBody UserInfo user) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
        return tokenService.genrateToken(auth);
    }
}
