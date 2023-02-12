package agenda.com.service;

import agenda.com.config.JwtTokenUtil;
import agenda.com.auth.JwtRequest;
import agenda.com.auth.JwtResponse;
import agenda.com.model.Role;
import agenda.com.model.User;
import agenda.com.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User findById(int id){
        Optional<User> user = userRepository.findById(id);
        return user.get();
    }
    public User uptade(User user, int  id){
        Optional<User> usernew = userRepository.findById(id);

        BeanUtils.copyProperties(user,usernew);
        return userRepository.save(usernew.get());
    }
    public void deleteUser(int  id){
        userRepository.deleteById(id);
    }
    public User findByEmail(String email){
        Optional<User> user = userRepository.findByEmail(email);
        return user.get();
    }
}
