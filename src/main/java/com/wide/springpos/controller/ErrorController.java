package com.wide.springpos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController {

    @RequestMapping("/error/unauthorized")
    public String loginForm() {
        return "error/unauthorized";
    }
}
