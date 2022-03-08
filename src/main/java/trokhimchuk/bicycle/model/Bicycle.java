package trokhimchuk.bicycle.model;

import trokhimchuk.bicycle.Entity.BicycleEntity;

public class Bicycle {

    private Long id;
    private String bicycleType;
    private String bicycleBrand;
    private Boolean isRented;


    public static Bicycle toModel(BicycleEntity bicycleEntity){
        Bicycle model = new Bicycle();
        model.setId(bicycleEntity.getId());
        model.setBicycleType(bicycleEntity.getBicycleType());
        model.setBicycleBrand(bicycleEntity.getBicycleBrand());
        model.setRented(bicycleEntity.getRented());
        return model;
    }

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

    @Override
    public String toString() {
        return "Bicycle{" +
                "id=" + id +
                ", bicycleType='" + bicycleType + '\'' +
                ", bicycleBrand='" + bicycleBrand + '\'' +
                ", isRented=" + isRented +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bicycle bicycle = (Bicycle) o;

        if (id != null ? !id.equals(bicycle.id) : bicycle.id != null) return false;
        if (bicycleType != null ? !bicycleType.equals(bicycle.bicycleType) : bicycle.bicycleType != null) return false;
        if (bicycleBrand != null ? !bicycleBrand.equals(bicycle.bicycleBrand) : bicycle.bicycleBrand != null)
            return false;
        return isRented != null ? isRented.equals(bicycle.isRented) : bicycle.isRented == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (bicycleType != null ? bicycleType.hashCode() : 0);
        result = 31 * result + (bicycleBrand != null ? bicycleBrand.hashCode() : 0);
        result = 31 * result + (isRented != null ? isRented.hashCode() : 0);
        return result;
    }
}
