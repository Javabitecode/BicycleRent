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
    private String typeBicycle;

  /*       @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "user_id")*/
    /*private User renter;*/

      /*  public String getAuthorName(){
            return renter != null ? renter.getUsername() : "<none>";
        }*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeBicycle() {
        return typeBicycle;
    }

    public void setTypeBicycle(String typeBicycle) {
        this.typeBicycle = typeBicycle;
    }

        /*public User getRenter() {
            return renter;
        }

        public void setRenter(User renter) {
            this.renter = renter;
        }*/
}


