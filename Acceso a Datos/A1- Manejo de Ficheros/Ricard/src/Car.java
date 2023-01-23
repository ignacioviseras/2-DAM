import java.io.Serializable;

public class Car implements Serializable {

    private Integer id;
    private String registre;
    private String brand;
    private String model;
    private String color;

    public Car(int id, String registre, String brand, String model, String color) {
        this.id = id; 
        this.registre = registre;
        this.brand = brand;
        this.model = model;
        this.color = color;
    }

    public Integer getId() {
        return id;
    }
    
    public String getRegistre() {
        return registre;
    }
    public void setRegistre(String registre) {
        this.registre = registre;
    }
    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }    
    
}
