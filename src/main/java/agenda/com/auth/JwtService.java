package agenda.com.auth;

import agenda.com.config.JwtTokenUtil;
import agenda.com.model.Role;
import agenda.com.model.User;
import agenda.com.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class JwtService {

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtTokenUtil jwtTokenUtil;
    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;
    public JwtResponse register(JwtRequest request) {
        var user = User.builder()
                .Id(UUID.randomUUID())
                .nome(request.getNome())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        var jwtToken = jwtTokenUtil.generateToken(user);
        return  JwtResponse.builder()
                .jwttoken(jwtToken)
                .build();
    }

    public JwtResponse authenticate(JwtRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtTokenUtil.generateToken(user);
        return JwtResponse.builder()
                .jwttoken(jwtToken)
                .build();
    }
}
