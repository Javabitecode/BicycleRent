package trokhimchuk.bicycle.Entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "usr")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;
    private int bicycleCount;
    private boolean active;

    @OneToMany(mappedBy = "userEntity",fetch = FetchType.EAGER)
    private Set<BicycleEntity> bicycleEntity;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    /*           ___Getter and setter___             */

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getBicycleCount() {
        return bicycleCount;
    }

    public void setBicycleCount(int bicycleCount) {
        this.bicycleCount = bicycleCount;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<BicycleEntity> getBicycleEntity() {
        return bicycleEntity;
    }

    public void setBicycleEntity(Set<BicycleEntity> bicycleEntity) {
        this.bicycleEntity = bicycleEntity;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", bicycleCount=" + bicycleCount +
                ", active=" + active +
                ", bicycleEntity=" + bicycleEntity +
                ", roles=" + roles +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (bicycleCount != that.bicycleCount) return false;
        if (active != that.active) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (bicycleEntity != null ? !bicycleEntity.equals(that.bicycleEntity) : that.bicycleEntity != null)
            return false;
        return roles != null ? roles.equals(that.roles) : that.roles == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + bicycleCount;
        result = 31 * result + (active ? 1 : 0);
        result = 31 * result + (bicycleEntity != null ? bicycleEntity.hashCode() : 0);
        result = 31 * result + (roles != null ? roles.hashCode() : 0);
        return result;
    }
}