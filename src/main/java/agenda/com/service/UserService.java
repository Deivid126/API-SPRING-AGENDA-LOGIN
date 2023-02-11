package agenda.com.service;

import agenda.com.model.User;
import agenda.com.repository.UserRepository;
import jakarta.validation.constraints.Email;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        final User usernew = userRepository.findByEmail(email);
        if (usernew == null) {
            throw new UsernameNotFoundException(email);
        }
        UserDetails user = org.springframework.security.core.userdetails.User.withUsername(usernew.getEmail()).password(usernew.getPassword()).authorities("USER").build();
        return user;
    }
    public User save(User user){
        Optional<User> usernew = userRepository.findById(user.getId());
        return userRepository.save(user);
    }
    public User findById(int id){
        Optional<User> user = userRepository.findById(id);
        return user.get();
    }
    public User uptade(User user, int id){
        Optional<User> usernew = userRepository.findById(id);

        BeanUtils.copyProperties(user,usernew);
        return userRepository.save(usernew.get());
    }
    public void deleteUser(int id){
        userRepository.deleteById(id);
    }
    public Optional<User> findByEmail(String email){
        return Optional.ofNullable(userRepository.findByEmail(email));
    }
}
