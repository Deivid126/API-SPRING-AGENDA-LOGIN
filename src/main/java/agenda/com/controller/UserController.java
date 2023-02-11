package agenda.com.controller;

import agenda.com.model.User;
import agenda.com.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> saveUser(@Valid @RequestBody User user){
        User usernew = userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(usernew);
    }
    @GetMapping("/{id}")
    public ResponseEntity<List<User>> getAllUser(@PathVariable(value = "id") int id){
        User user = userService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(Collections.singletonList(user));
    }


}
