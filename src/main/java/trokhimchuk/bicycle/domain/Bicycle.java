package trokhimchuk.bicycle.domain;


import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table
public class Bicycle {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String bicycleType;
    private String bicycleBrand;

  /*       @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "user_id")*/
    /*private User renter;*/

      /*  public String getAuthorName(){
            return renter != null ? renter.getUsername() : "<none>";
        }*/


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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bicycle bicycle = (Bicycle) o;

        if (id != null ? !id.equals(bicycle.id) : bicycle.id != null) return false;
        if (bicycleType != null ? !bicycleType.equals(bicycle.bicycleType) : bicycle.bicycleType != null) return false;
        return bicycleBrand != null ? bicycleBrand.equals(bicycle.bicycleBrand) : bicycle.bicycleBrand == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (bicycleType != null ? bicycleType.hashCode() : 0);
        result = 31 * result + (bicycleBrand != null ? bicycleBrand.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Bicycle{" +
                "id=" + id +
                ", bicycleType='" + bicycleType + '\'' +
                ", bicycleBrand='" + bicycleBrand + '\'' +
                '}';
    }
}


