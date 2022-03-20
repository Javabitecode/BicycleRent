package trokhimchuk.bicycle.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import trokhimchuk.bicycle.Entity.Role;
import trokhimchuk.bicycle.Entity.UserEntity;
import trokhimchuk.bicycle.exception.UserAlreadyExistException;
import trokhimchuk.bicycle.exception.UserNotFoundException;
import trokhimchuk.bicycle.model.User;
import trokhimchuk.bicycle.repo.BicycleRepository;
import trokhimchuk.bicycle.repo.RoleRepository;
import trokhimchuk.bicycle.repo.UserRepository;

import java.util.Collection;
import java.util.stream.Collectors;


@Service
public class UserService implements UserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    private final BicycleRepository bicycleRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository, BicycleRepository bicycleRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.bicycleRepository = bicycleRepository;
        this.roleRepository = roleRepository;
    }

    public UserEntity registration(UserEntity userEntity) throws UserAlreadyExistException {
        if (userRepository.findByUsername(userEntity.getUsername()) != null) {
            throw new UserAlreadyExistException("User with this name exists");
        }
        userEntity.setRoles((Collection<Role>) roleRepository.findByName("ROLE_USER")); // "ADMIN"
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        return userRepository.save(userEntity);
    }

    public User getOne(Long id) throws UserNotFoundException {
        UserEntity user = userRepository.findById(id).get();
        if (user == null) {
            throw new UserNotFoundException("User not found.");
        }
        return User.toModel(user);
    }

    public Long delete(Long id) {
        userRepository.deleteById(id);
        return id;
    }
    public UserEntity findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = findByUsername(username);
        return new org.springframework.security.core.userdetails.User(userEntity.getUsername(),
                userEntity.getPassword(),
                mapRolesToAuthorities(userEntity.getRoles()));
    }
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities (Collection<Role> roles){
        return roles.stream().map(r -> new SimpleGrantedAuthority(r.getName())).collect(Collectors.toList());
    }
}
