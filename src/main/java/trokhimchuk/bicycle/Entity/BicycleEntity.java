package trokhimchuk.bicycle.Entity;


import javax.persistence.*;

@Entity
@Table
public class BicycleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String bicycleType;
    private String bicycleBrand;
    private Boolean isRented;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;


    /*           ___Getter and setter___             */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBicycleType() {
        return bicycleType;
    }

    public void setBicycleType(String bicycleType) {
        this.bicycleType = bicycleType;
    }

    public String getBicycleBrand() {
        return bicycleBrand;
    }

    public void setBicycleBrand(String bicycleBrand) {
        this.bicycleBrand = bicycleBrand;
    }

    public Boolean getRented() {
        return isRented;
    }

    public void setRented(Boolean rented) {
        isRented = rented;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    @Override
    public String toString() {
        return "BicycleEntity{" +
                "id=" + id +
                ", bicycleType='" + bicycleType + '\'' +
                ", bicycleBrand='" + bicycleBrand + '\'' +
                ", isRented=" + isRented +
                ", userEntity=" + userEntity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BicycleEntity that = (BicycleEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (bicycleType != null ? !bicycleType.equals(that.bicycleType) : that.bicycleType != null) return false;
        if (bicycleBrand != null ? !bicycleBrand.equals(that.bicycleBrand) : that.bicycleBrand != null) return false;
        if (isRented != null ? !isRented.equals(that.isRented) : that.isRented != null) return false;
        return userEntity != null ? userEntity.equals(that.userEntity) : that.userEntity == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (bicycleType != null ? bicycleType.hashCode() : 0);
        result = 31 * result + (bicycleBrand != null ? bicycleBrand.hashCode() : 0);
        result = 31 * result + (isRented != null ? isRented.hashCode() : 0);
        result = 31 * result + (userEntity != null ? userEntity.hashCode() : 0);
        return result;
    }
}

