package trokhimchuk.bicycle.domain;


import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table
@ToString(of = {"id", "typeBicycle"})
@EqualsAndHashCode(of = {"id"}) //исправить
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


}


