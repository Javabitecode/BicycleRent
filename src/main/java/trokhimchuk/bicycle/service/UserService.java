package trokhimchuk.bicycle.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import trokhimchuk.bicycle.Entity.BicycleEntity;
import trokhimchuk.bicycle.Entity.Role;
import trokhimchuk.bicycle.Entity.UserEntity;
import trokhimchuk.bicycle.exception.UserAlreadyExistException;
import trokhimchuk.bicycle.exception.UserNotFoundException;
import trokhimchuk.bicycle.model.User;
import trokhimchuk.bicycle.repo.BicycleRepository;
import trokhimchuk.bicycle.repo.UserRepository;

import java.util.Collections;


@Service
public class UserService implements UserDetailsService {
    @Lazy
    @Autowired
    private PasswordEncoder passwordEncoder;
    private final BicycleRepository bicycleRepository;
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository, BicycleRepository bicycleRepository) {
        this.userRepository = userRepository;
        this.bicycleRepository = bicycleRepository;
    }

    public UserEntity registration(UserEntity user) throws UserAlreadyExistException {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new UserAlreadyExistException("User with this name exists");
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER)); // "ADMIN"
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User getOne(Long id) throws UserNotFoundException {
        UserEntity user = userRepository.findById(id).get();
        if (user == null) {
            throw new UserNotFoundException("User not found");
        }
        return User.toModel(user);
    }

    public Long delete(Long id) {
        userRepository.deleteById(id);
        return id;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = new UserEntity();
        return userRepository.findByUsername(username);
    }
}
