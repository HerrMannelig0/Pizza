package org.sampleproject.pizza.pizzaservice.service;

import org.sampleproject.pizza.pizzaservice.model.Pizza;

public interface PizzaService {

    void save(Pizza pizza);
    Pizza findByName(String name);
}
