package com.example.models;

/**
 * Passanger
 */
public class Passanger {
    private Integer id;
    private Integer car_id;
    private String name;
    private Integer age;
    private Integer weight;

    public Passanger() {
    }

    public Passanger(Integer id, Integer car_id, String name, Integer age, Integer weight) {
        this.id = id;
        this.car_id = car_id;
        this.name = name;
        this.age = age;
        this.weight = weight;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCar_id() {
        return car_id;
    }

    public void setCar_id(Integer car_id) {
        this.car_id = car_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        String id = "   Identificador: "+ this.getId().toString() ;
        String carId = "   Coche: "+ this.getCar_id().toString() ;
        String name = "   Nombre: "+this.getName() ;
        String age = "   Edad: "+ this.getAge().toString() ;
        String weight = "   Peso: "+this.getWeight().toString() + "   ";
        return id + carId + name + age + weight;
    }
}