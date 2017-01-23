package org.sampleproject.pizza.pizzaservice;

import org.sampleproject.pizza.pizzaservice.model.Ingredient;
import org.sampleproject.pizza.pizzaservice.model.Pizza;
import org.sampleproject.pizza.pizzaservice.repository.IngredientRepository;
import org.sampleproject.pizza.pizzaservice.service.PizzaService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Bean
    public Pizza testRepo(PizzaService pizzaService, IngredientRepository ingredientRepository){
        Ingredient olive = new Ingredient("olive");
        Ingredient tuna = new Ingredient("tuna");
        Ingredient pommodore = new Ingredient("pommodore");
        Ingredient ananas = new Ingredient("ananas");

        List<Ingredient> ingredientList = new ArrayList<>();
        List<Ingredient> havaiiList = new ArrayList<>();
        ingredientList.add(olive);
        ingredientList.add(tuna);
        ingredientList.add(pommodore);

        ingredientRepository.save(ingredientList);

        havaiiList.add(pommodore);
        havaiiList.add(ananas);

        ingredientRepository.save(havaiiList);
        Pizza p = new Pizza("MultiKulti", 40, ingredientList);
        Pizza h = new Pizza("HAVAII", 35, havaiiList);
        pizzaService.save(p);
        pizzaService.save(h);
        return p;
    }

}
