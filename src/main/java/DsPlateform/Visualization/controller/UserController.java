package DsPlateform.Visualization.controller;
import DsPlateform.Visualization.entity.UserInfo;
import DsPlateform.Visualization.repository.UserInfoRepository;
import DsPlateform.Visualization.service.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.security.Principal;

@RestController
@RequestMapping("/Api")
public class UserController {

    @Autowired
    private UserInfoRepository userRepository;

    @Autowired
    private CloudinaryService cloudinaryService;

    @GetMapping
    public String home(Principal principal) {
        return "hello " + principal.getName();
    }

    @PutMapping("/user/UpdateProfile")
    public ResponseEntity<UserInfo> updateUserProfile(
            @RequestParam("imageUrl") MultipartFile image,
            @RequestParam(value = "name", required = false) String name,
            Principal principal) throws IOException {

        String email = principal.getName();
        UserInfo user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Upload to Cloudinary
        String imageUrl = cloudinaryService.uploadImage(image);
        user.setImageUrl(imageUrl);

        // Optional name update
        if (name != null) {
            user.setName(name);
        }

        userRepository.save(user);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/user/profile")
    public UserInfo getProfile(Principal principal) {
        return userRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
