package com.test.hplus.controllers;

import com.test.hplus.beans.Login;
import com.test.hplus.beans.Product;
import com.test.hplus.beans.User;
import com.test.hplus.exceptions.ApplicationException;
import com.test.hplus.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
//Model with name "login" will be stored to session
//Model with this name defined in ControllerAdvice
//If not - need to create method which return model with this name
@SessionAttributes("login")
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public String login(@ModelAttribute("login") Login login, HttpSession session) {
        session.setMaxInactiveInterval(60);
        session.setAttribute("productForAdvertising",
                Product.builder()
                        .id(2)
                        .name("mineralwater-lemonlime")
                        .imagePath("images/mineralwater-lemonlime.jpg")
                        .build()
        );
        User user = userRepository.searchByName(login.getUsername());
        if (user == null) {
            throw new ApplicationException("User not found");
        }
        return "forward:/userProfile";
    }

    //This ExceptionHandler has more priority then global
/*    @ExceptionHandler(ApplicationException.class)
    public String handleException() {
        System.out.println("in exception handler of Login Controller");
        return "error";
    }*/
}

