package org.pizzaproject.model;


import java.util.ArrayList;
import java.util.List;

public class Ingredients {

    private List<Ingredient> ingredientsList;

    public Ingredients() {
        ingredientsList = new ArrayList<>();
    }

    public Ingredients(List<Ingredient> ingredientsList) {
        this.ingredientsList = ingredientsList;
    }

    public List<Ingredient> getIngredientsList() {
        return ingredientsList;
    }

    public void setIngredientsList(List<Ingredient> ingredientsList) {
        this.ingredientsList = ingredientsList;
    }

    public void add(Ingredient ingredient){
        ingredientsList.add(ingredient);
    }

    @Override
    public String toString() {
        return "Ingredients{" +
                "ingredientsList=" + ingredientsList +
                '}';
    }
}
