package com.example.csrfdemo4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@Controller
@RequestMapping("/product")
public class ProductController {
    private final Logger logger =
            Logger.getLogger(ProductController.class.getName());

    @PostMapping("/add")
    public String add(@RequestParam String name){
        logger.info("Adding Product: "+name);
        return "main";
    }

    @ResponseBody
    @PostMapping("/hello")
    public String hello(){
        return "Hello!";
    }
    //curl -X POST -H "X-IDENTIFIER:12345" -H "X-CSRF-TOKEN:" localhost:8080/product/bye
    @ResponseBody
    @GetMapping("/welcome")
    public String hello2(){
        return "GET Hello!";
    }
    @ResponseBody
    @PostMapping("/bye")
    public String bye(){
        return "POST Bye!";
    }
}
