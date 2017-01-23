package org.sampleproject.pizza.pizzaservice.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Pizza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private int price;

    @ManyToMany
    private List<Ingredient> ingreadients;

    public Pizza() {
    }

    public Pizza(String name) {
        this.name = name;
    }

    public Pizza(String name, int price, List<Ingredient> ingreadients) {
        this.name = name;
        this.price = price;
        this.ingreadients = ingreadients;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<Ingredient> getIngreadients() {
        return ingreadients;
    }

    public void setIngreadients(List<Ingredient> ingreadients) {
        this.ingreadients = ingreadients;
    }

}
