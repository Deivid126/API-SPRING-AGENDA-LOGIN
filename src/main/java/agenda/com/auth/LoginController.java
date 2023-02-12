package agenda.com.auth;

import agenda.com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/auth/")
public class LoginController {

    @Autowired
    JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<JwtResponse> register(
            @RequestBody JwtRequest request
    ) {
        return ResponseEntity.ok(jwtService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<JwtResponse> authenticate(
            @RequestBody JwtRequest request
    ) {
        return ResponseEntity.ok(jwtService.authenticate(request));
    }

}
