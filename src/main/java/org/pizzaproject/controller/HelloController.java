package org.pizzaproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {

    @RequestMapping("/")
    public String hello(){
        return "hello";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }



}
