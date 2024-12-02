package com.loop.springBootSecurity.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public String sayHello(){
        return "hello world";
    }

    @GetMapping("/get-csrf")
    public CsrfToken getCSRF(HttpServletRequest req){
        return (CsrfToken) req.getAttribute("_csrf");
    }
}
