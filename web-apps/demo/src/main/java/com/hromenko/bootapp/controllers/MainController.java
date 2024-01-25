package com.hromenko.bootapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/web")
public class MainController {

    @GetMapping()
    public String greetings(Model model){
        model.addAttribute("title", "Main Page");
        return "mainPage";
    }

    @GetMapping("/about")
    public String about(Model model){
        return "aboutPage";
    }
}
