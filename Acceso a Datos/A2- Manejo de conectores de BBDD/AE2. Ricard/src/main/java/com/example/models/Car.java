package com.example.models;

public class Car {
    private Integer id;
    private String reg;
    private String brand;
    private String model;
    private String color;

    public Car(Integer id, String reg, String brand, String model, String color) {
        this.id = id;
        this.reg = reg;
        this.brand = brand;
        this.model = model;
        this.color = color;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReg() {
        return reg;
    }

    public void setReg(String reg) {
        this.reg = reg;
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

    @Override
    public String toString() {
        String id = "   Identificador: " + this.getId();

        String reg = "   Matrícula: " + this.getReg();

        String brand = "   Marca: " + this.getBrand();

        String model = "   Modelo: " + this.getModel();

        String color = "   Color: " + this.getColor();

        return id + reg + brand + model + color;
    }
}
