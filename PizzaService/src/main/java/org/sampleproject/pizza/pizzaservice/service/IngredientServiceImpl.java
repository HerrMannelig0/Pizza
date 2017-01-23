package org.sampleproject.pizza.pizzaservice.service;

import org.sampleproject.pizza.pizzaservice.model.Ingredient;
import org.sampleproject.pizza.pizzaservice.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class IngredientServiceImpl implements IngredientService {

    private IngredientRepository repository;

    @Autowired
    public IngredientServiceImpl(IngredientRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(Ingredient ingredient) {
        repository.save(ingredient);
    }
}
