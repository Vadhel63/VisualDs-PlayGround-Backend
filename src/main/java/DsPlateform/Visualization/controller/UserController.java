package DsPlateform.Visualization.controller;

import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
public class UserController {

    @GetMapping
    public  String Home(Principal principal)
    {
        return "hello "+principal.getName();
    }


    
}
