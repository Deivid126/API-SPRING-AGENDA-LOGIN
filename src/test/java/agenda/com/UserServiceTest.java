package agenda.com;

import agenda.com.model.User;
import agenda.com.repository.UserRepository;
import agenda.com.service.UserService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void testFindById() {
        User user = new User();
        user.setId(1);
        user.setNome("John");
        Mockito.when(userRepository.findById(1)).thenReturn(Optional.of(user));

        Optional<User> result = userService.findById(1);

        assertTrue(result.isPresent());
        assertEquals("John", result.get().getNome());
    }

    @Test
    public void testUpdate() {
        User user = new User();
        user.setId(1);
        user.setNome("Jane");
        Mockito.when(userRepository.findById(1)).thenReturn(Optional.of(user));
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);

        User updatedUser = userService.uptade(user, 1);

        assertEquals("Jane", updatedUser.getNome());
        Mockito.verify(userRepository).findById(1);
        Mockito.verify(userRepository).save(Mockito.any(User.class));
    }

    @Test
    public void testDeleteUser() {
        doNothing().when(userRepository).deleteById(1);

        userService.deleteUser(1);

        Mockito.verify(userRepository).deleteById(1);
    }

    @Test
    public void testFindByEmail() {
        User user = new User();
        user.setId(1);
        user.setNome("John");
        Mockito.when(userRepository.findByEmail("john@example.com")).thenReturn(Optional.of(user));

        User result = userService.findByEmail("john@example.com");

        assertEquals("John", result.getNome());
        Mockito.verify(userRepository).findByEmail("john@example.com");
    }
}

