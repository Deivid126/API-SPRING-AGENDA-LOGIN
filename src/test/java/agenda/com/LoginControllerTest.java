package agenda.com;

import agenda.com.auth.JwtRequest;
import agenda.com.auth.JwtResponse;
import agenda.com.auth.JwtService;
import agenda.com.auth.LoginController;
import agenda.com.repository.TaskRepository;
import agenda.com.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(LoginController.class)
public class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private TaskRepository taskRepository;

    @Test
    public void testRegister() throws Exception {
        // Create a JwtRequest object with sample credentials
        JwtRequest request = new JwtRequest("name","name@gmail.com", "password");

        // Create a JwtResponse object with a sample JWT token
        JwtResponse response = new JwtResponse("sample-token");

        // Mock the jwtService.register method to return the sample response
        Mockito.when(jwtService.register(request)).thenReturn(response);

        // Send a POST request to the /api/v1/auth/register endpoint with the sample request object
        mockMvc.perform(post("/api/v1/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.jwttoken").value("sample-token"));
    }

    @Test
    public void testAuthenticate() throws Exception {
        // Create a JwtRequest object with sample credentials
        JwtRequest request = new JwtRequest("name","name@gmail.com", "password");

        // Create a JwtResponse object with a sample JWT token
        JwtResponse response = new JwtResponse("sample-token");

        // Mock the jwtService.authenticate method to return the sample response
        Mockito.when(jwtService.authenticate(request)).thenReturn(response);

        // Send a POST request to the /api/v1/auth/authenticate endpoint with the sample request object
        mockMvc.perform(post("/api/v1/auth/authenticate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.jwttoken").value("sample-token"));
    }
}
