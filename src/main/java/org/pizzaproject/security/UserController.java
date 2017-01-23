package org.pizzaproject.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@Scope("session")
public class UserController {

    private RestTemplate template = new RestTemplate();

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${url.userservice}")
    private String userServiceUrl;

    @RequestMapping("/addUser")
    public String addUserGet(Model model){
        model.addAttribute("user", new User());
        return "newUserForm";
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUserPost(@ModelAttribute @Valid User user, Errors errors, HttpServletRequest request){

        if (processErrorsIfAppears(errors, request)) return "redirect:/wrongCredentials";

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        template.postForObject(userServiceUrl + "/user/add", user, User.class);

        return "hello";
    }

    @RequestMapping("/wrongCredentials")
    public String wrongCredentials(Model model, HttpServletRequest request){

        model.addAttribute("message", request.getSession().getAttribute("message"));
        return "wrongCredentials";
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
