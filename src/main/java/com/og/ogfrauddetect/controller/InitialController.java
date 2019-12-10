package com.og.ogfrauddetect.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class InitialController {
    @RequestMapping("/")
    public String login() {
        return "redirect:login.html";
    }
}
