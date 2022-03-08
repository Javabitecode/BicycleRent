package trokhimchuk.bicycle.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    public void clearRentedBicycles(Long idUser){
        BicycleEntity bicycleFromDB = bicycleRepository.findByUserEntity(userRepository.findById(idUser).get());
        bicycleFromDB.setRented(false);
        bicycleFromDB.setUserEntity(null);

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = new UserEntity();
        return userRepository.findByUsername(username);
    }
}
