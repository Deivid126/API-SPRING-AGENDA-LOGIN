package agenda.com;

import agenda.com.auth.JwtRequest;
import agenda.com.auth.JwtResponse;
import agenda.com.auth.JwtService;
import agenda.com.config.JwtTokenUtil;
import agenda.com.enums.Role;
import agenda.com.model.User;
import agenda.com.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class JwtServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private JwtTokenUtil jwtTokenUtil;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private JwtService jwtService;

    @Test
    public void testRegister() {

        JwtRequest jwtRequest = new JwtRequest();
        jwtRequest.setNome("Test User");
        jwtRequest.setEmail("test@test.com");
        jwtRequest.setPassword("test123");

        User user = User.builder()
                .nome("Test User")
                .email("test@test.com")
                .password("hashedpassword")
                .role(Role.USER)
                .build();


        when(userRepository.save(any(User.class))).thenReturn(user);
        when(jwtTokenUtil.generateToken(any(User.class))).thenReturn("testtoken");
        when(passwordEncoder.encode(anyString())).thenReturn("hashedpassword");


        JwtResponse response = jwtService.register(jwtRequest);

        assertNotNull(response.getJwttoken());
        assertEquals("testtoken", response.getJwttoken());
    }
    @Test
    public void testAuthenticate() {

        JwtRequest request = new JwtRequest("John Doe", "john.doe@example.com", "password");

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
        );


        when(authenticationManager.authenticate(authenticationToken)).thenReturn(authenticationToken);

        when(userRepository.findByEmail(request.getEmail())).thenReturn(
                Optional.of(new User(1,"john.doe@example.com","John Doe", "encodedPassword", Role.USER, new ArrayList<>())));


        when(jwtTokenUtil.generateToken(any(User.class))).thenReturn("jwtToken");


        JwtResponse response = jwtService.authenticate(request);


        assertEquals("jwtToken", response.getJwttoken());
    }
}
