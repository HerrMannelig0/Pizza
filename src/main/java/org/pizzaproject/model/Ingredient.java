package org.pizzaproject.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Ingredient implements Serializable{

    @Min(value = 1, message = "Ingredient Id must be positive!")
    private int ingredientId;

    @NotNull(message = "Specify ingredient's name!")
    private String ingredient;

    public Ingredient() {
    }

    public Ingredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public int getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "ingredientId=" + ingredientId +
                ", ingredient='" + ingredient + '\'' +
                '}';
    }
}

