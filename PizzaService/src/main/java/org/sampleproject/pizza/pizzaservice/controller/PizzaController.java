package org.sampleproject.pizza.pizzaservice.controller;

import org.sampleproject.pizza.pizzaservice.model.Pizza;
import org.sampleproject.pizza.pizzaservice.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PizzaController {

    @Autowired
    private PizzaService service;

    @RequestMapping("/pizza")
    public Pizza pizza(@RequestParam(value = "name", defaultValue = "Margerita") String name){
        // return new Pizza("Matador");

        return service.findByName(name);

    }

}
