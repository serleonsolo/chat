package com.chat.controller;

import com.chat.model.Message;
import com.chat.model.User;
import com.chat.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MessageController {

    @Autowired
    MessageRepository repository;

    @GetMapping("/")
    public String greeting()
    {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "") String filter,
                       Model model) {
        Iterable<Message> messages;
        if(filter.isEmpty() || filter == null)
            messages = repository.findAll();
        else
            messages = repository.findByTag(filter);
        model.addAttribute("messages", messages);
        model.addAttribute("filter", filter);
        return "main";
    }

    @PostMapping("/main")
    public String add(@AuthenticationPrincipal User user,
                      @RequestParam String text,
                      @RequestParam String tag,
                      Model model)
    {
        Message message = new Message(text,tag,user);
        repository.save(message);

        Iterable<Message> messages = repository.findAll();
        model.addAttribute("messages", messages);
        model.addAttribute("filter", "");
        return "main";
    }
}
