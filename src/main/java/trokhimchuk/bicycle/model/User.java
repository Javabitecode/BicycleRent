package trokhimchuk.bicycle.model;

import trokhimchuk.bicycle.Entity.UserEntity;

import java.util.List;
import java.util.stream.Collectors;

public class User {
    private Long id;
    private String username;
    private int bicycleCount;


    public static User toModel(UserEntity entity) {
        User model = new User();
        model.setId(entity.getId());
        model.setUsername(entity.getUsername());
        model.setBicycleCount(entity.getBicycleCount());
        return model;
    }


    public User() {
    }

    public int getBicycleCount() {
        return bicycleCount;
    }

    public void setBicycleCount(int bicycleCount) {
        this.bicycleCount = bicycleCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
