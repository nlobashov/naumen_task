package ru.nlobashov.naumen.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Products")
public class Product {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @NotEmpty(message = "Наименование не может быть пустым")
    @Size(min = 3, max = 100, message = "Наименование должно быть от 3 до 100 символов")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "quantity")
    @Min(value = 0, message = "Количество не может быть отрицательным")
    private double quantity;

    public Product(String name, String description, double quantity) {
        this.name = name;
        this.description = description;
        this.quantity = quantity;
    }

    public Product() {}

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
}