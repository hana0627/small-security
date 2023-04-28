package com.hana.smallsecurity.application.controller;

import com.hana.smallsecurity.application.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class MainPageController {
    private final ProductService productService;

    @GetMapping("/main")
    public String main(Authentication auth, Model model) {
        model.addAttribute("username", auth.getName());
        model.addAttribute("products", productService.findAll());
        return "/main";
    }
}
