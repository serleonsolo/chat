package com.chat.controller;

import com.chat.model.Role;
import com.chat.model.User;
import com.chat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/registration")
    public String registration(Map<String,Object> model) {
        return "registration";
    }

    @PostMapping("/registration")
    public String add(User user,
                      Model model)
    {
        User userDb = userRepository.findByUsername(user.getUsername());

        if(userDb != null)
        {
            model.addAttribute("message","User Already Exists");
            return "registration";
        }

        user.setIsActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);
        model.addAttribute("message","User Added Successfully");
        return "redirect:/login";
    }
}
