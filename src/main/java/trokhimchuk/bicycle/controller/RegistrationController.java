package trokhimchuk.bicycle.controller;

import org.springframework.web.bind.annotation.*;
import trokhimchuk.bicycle.domain.Role;
import trokhimchuk.bicycle.domain.User;
import trokhimchuk.bicycle.repo.UserRepository;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/registration")
public class RegistrationController {

    private final UserRepository userRepository;
    public RegistrationController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/registration")
    public List<User> registration(){
        return userRepository.findAll(); //для теста исправить!!!!
    }

    @PostMapping
    public User addUser(@RequestBody User user){
        User userFromDB = userRepository.findByUsername(user.getUsername());
        if (userFromDB!= null) {
            return null; //исправить!!!!!!!!!
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        return userRepository.save(user);

    }
}
