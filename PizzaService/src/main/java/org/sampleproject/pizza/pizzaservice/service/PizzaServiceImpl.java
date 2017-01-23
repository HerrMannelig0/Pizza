package org.sampleproject.pizza.pizzaservice.service;

import org.sampleproject.pizza.pizzaservice.model.Pizza;
import org.sampleproject.pizza.pizzaservice.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PizzaServiceImpl implements PizzaService {

    private PizzaRepository repository;

    @Autowired
    public PizzaServiceImpl(PizzaRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(Pizza pizza) {
        repository.save(pizza);
    }

    @Override
    public Pizza findByName(String name) {
        return repository.findByName(name);
    }
}
