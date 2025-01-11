package com.limo.ssiach2ex1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/home")
    public String home() {
        return "home.html";
    }

    @GetMapping("/error401")
    public String error401() {
        return "error401.html";
    }

    @GetMapping("/error403")
    public String error403() {
        return "error403.html";
    }

}
