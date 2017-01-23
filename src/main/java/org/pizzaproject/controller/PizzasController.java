package org.pizzaproject.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.pizzaproject.model.Ingredient;
import org.pizzaproject.model.Ingredients;
import org.pizzaproject.model.IngredientsNumber;
import org.pizzaproject.model.Pizza;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/pizza")
@Scope("session")
public class PizzasController {

    private static final Log logger = LogFactory.getLog(PizzasController.class);

    @Value("${url.pizzaservice}")
    private String pizzaserviceUrl;


    @Value("${url.pizzaservice.pizza}")
    private String pizzaUrl;

    private RestTemplate template = new RestTemplate();

    @RequestMapping
    public String pizzaDetails(Model model, @RequestParam(value = "name", defaultValue = "Mexico") String name){
        logger.debug("Pizza details requested: " + name);
        template.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        Pizza pizza = template.getForObject(pizzaUrl + "?name=" + name, Pizza.class);
        model.addAttribute("pizza", pizza);
        return "pizzaDetails";
    }

    @RequestMapping("/newPizza")
    public String addNewPizzaGet(Model model){
        model.addAttribute("newPizza", new Pizza());
        model.addAttribute("ingredientsNumber", new IngredientsNumber());
        return "addNewPizza";
    }

    @RequestMapping(value = "/newPizza", method = RequestMethod.POST)
    public String addNewPizzaPost(@ModelAttribute @Valid Pizza newPizza, Errors errors, @ModelAttribute("ingredientsNumber") IngredientsNumber ingredientsNumber, HttpServletRequest request){
        logger.debug("Creating new pizza");

        if (processErrorsIfAppears(errors, request)) return "hello";

        request.getSession().setAttribute("price", newPizza.getPrice());
        request.getSession().setAttribute("name", newPizza.getName());

        return "redirect:/pizza/addIngredients" +
                "?ingredientsNumber=" + ingredientsNumber;
    }

    @RequestMapping("/addIngredients")
    public String addIngredientsGet(Model model, @RequestParam("ingredientsNumber") String number){
        Ingredients ingredients = new Ingredients();
        IngredientsNumber ingredientsNumber = new IngredientsNumber(number);

        for(int i=0; i<ingredientsNumber.getValue(); i++){
            ingredients.add(new Ingredient());
        }

        model.addAttribute("ingredients", ingredients);
        return "addIngredients";
    }

    @RequestMapping(value = "/addIngredients", method = RequestMethod.POST)
    public String addIngredientsPost(@ModelAttribute Ingredients newIngredients, HttpServletRequest request){
        logger.debug("Adding ingredients to new pizza");
        Pizza pizza = new Pizza();
        String pizzaName = (String) request.getSession().getAttribute("name");
        int pizzaPrice = (int) request.getSession().getAttribute("price");
        pizza.setName(pizzaName);
        pizza.setPrice(pizzaPrice);
        pizza.setIngredients(newIngredients);
        template.postForObject(pizzaserviceUrl + "/newpizza", pizza, Pizza.class);
        return "redirect:/pizza/all";
    }

    @RequestMapping("/all")
    public String allPizzas(Model model){

        List<Pizza> pizzas = template.getForObject(pizzaUrl + "/all", List.class);
        model.addAttribute("pizzas", pizzas);
        return "pizzaList";
    }

    private boolean processErrorsIfAppears(Errors errors, HttpServletRequest request) {
        if(errors.hasErrors()){
            final List<String> message = new ArrayList<>();
            List<ObjectError> errorList = errors.getAllErrors();
            errorList.stream().forEach(e -> {
                message.add(e.getDefaultMessage());
            });

            request.getSession().setAttribute("message", message);

            return true;
        }
        return false;
    }

}
