package com.test.hplus.controllers;

import com.test.hplus.beans.Login;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class UserProfileController {

    //Retrieving model "login" from session
    //Retrieving attribute "counter" from request
    @PostMapping("/userProfile")
    public String getUserProfile(@SessionAttribute("login") Login login, Model model, @RequestAttribute("counter") Integer counter) {
        System.out.println("in user profile controller");
        System.out.println("userName from session: " + login.getUsername());
        //by call to user repository can to get all the user information and send to profile JSP
        //send userName to profile JSP for welcoming line
        model.addAttribute("userName", login.getUsername());
        //send counter to profile JSP for welcoming line
        model.addAttribute("counter", counter);
        return "profile";
    }
}
