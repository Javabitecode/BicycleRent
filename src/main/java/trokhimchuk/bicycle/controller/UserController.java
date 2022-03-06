package trokhimchuk.bicycle.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import trokhimchuk.bicycle.Entity.UserEntity;
import trokhimchuk.bicycle.exception.NotEntityException;
import trokhimchuk.bicycle.exception.UserNotFoundException;
import trokhimchuk.bicycle.repo.UserRepository;
import trokhimchuk.bicycle.service.UserService;

import java.util.Optional;

@RestController
@RequestMapping("user")
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping
    public Iterable<UserEntity> userList() {
        return userRepository.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity getOneUser(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(userService.getOne(id));
        } catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(userService.delete(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @PutMapping("/{id}")
    public UserEntity update(@PathVariable("id") Long id,
                             @RequestBody UserEntity user) {
/*
        UserEntity userFromDB = userRepository.findById(id).get();
        userFromDB.setPassword(user.getPassword());
        userFromDB.setRoles(user.getRoles());
        userFromDB.setUsername(user.getUsername());
        userFromDB.setBicycleCount(user.getBicycleCount());
        userFromDB.setActive(user.getActive());
        return userRepository.save(userFromDB);
*/

    }


}
