package trokhimchuk.bicycle.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import trokhimchuk.bicycle.domain.User;
import trokhimchuk.bicycle.repo.UserRepository;

@RestController
@RequestMapping("user")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping()
    public Iterable<User> userList() {
        return userRepository.findAll();
    }

    @PutMapping("{id}")
    public User update(@PathVariable("id") User userFromDB,
                       @RequestBody User user) {
        BeanUtils.copyProperties(user, userFromDB, "id");
        return userRepository.save(userFromDB);
    }
}
