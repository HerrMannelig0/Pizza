package org.sampleproject.pizza.pizzaservice.repository;

import org.sampleproject.pizza.pizzaservice.model.Pizza;
import org.springframework.data.repository.CrudRepository;

public interface PizzaRepository extends CrudRepository<Pizza, Integer>{

    Pizza findByName(String name);

}
