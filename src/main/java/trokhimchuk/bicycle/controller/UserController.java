package trokhimchuk.bicycle.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import trokhimchuk.bicycle.Entity.UserEntity;
import trokhimchuk.bicycle.exception.UserNotFoundException;
import trokhimchuk.bicycle.repo.UserRepository;
import trokhimchuk.bicycle.service.UserService;

@RestController

@RequestMapping("user")
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public Iterable<UserEntity> userList() {
        return userRepository.findAll();
    }

    @GetMapping("/get")
    public ResponseEntity getUser(@AuthenticationPrincipal UserEntity userEntity){
        return ResponseEntity.of(userRepository.findById(userEntity.getId()));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("{id}")
    public ResponseEntity getOneUser(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(userService.getOne(id));
        } catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(userService.delete(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("{id}")
    public UserEntity update(@PathVariable("id") UserEntity userFromDB,
                             @RequestBody UserEntity userEntity) {
        BeanUtils.copyProperties(userEntity, userFromDB, "id");
        return userRepository.save(userFromDB);
    }

}
