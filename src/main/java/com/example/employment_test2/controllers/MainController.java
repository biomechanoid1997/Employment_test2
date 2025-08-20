package com.example.employment_test2.controllers;

import com.example.employment_test2.services.DatabaseImportService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {

@GetMapping
    public String getMainPage(){

    return "index";
}
}
