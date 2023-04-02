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
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;


    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable(value = "id") UUID id){
        Optional<User> user = userService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body((user.get()));
    }
    @GetMapping
    public ResponseEntity<List<User>> getAllUser()
    {
        List<User> list = userService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }


}
