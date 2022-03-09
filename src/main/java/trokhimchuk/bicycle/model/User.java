package trokhimchuk.bicycle.model;

import trokhimchuk.bicycle.Entity.UserEntity;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (bicycleCount != user.bicycleCount) return false;
        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        return username != null ? username.equals(user.username) : user.username == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + bicycleCount;
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", bicycleCount=" + bicycleCount +
                '}';
    }
}
