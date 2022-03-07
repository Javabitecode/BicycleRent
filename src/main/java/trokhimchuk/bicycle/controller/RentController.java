package trokhimchuk.bicycle.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import trokhimchuk.bicycle.Entity.UserEntity;

@RestController
public class RentController {

/*    //@AuthenticationPrincipal UserEntity userentity;

    @PutMapping("{id}")
    public UserEntity bicycleRent(@PathVariable("id") UserEntity userFromDB,
                             @RequestBody UserEntity userEntity) {
        BeanUtils.copyProperties(userEntity, userFromDB, "id"); //Исправить
        return userRepository.save(userFromDB);
    }*/
}
