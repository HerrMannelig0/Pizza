package org.sampleproject.pizza.pizzaservice.repository;

import org.sampleproject.pizza.pizzaservice.model.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, Integer> {
}
