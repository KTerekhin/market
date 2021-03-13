package ru.gb.thymeleaf.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.rest.service.model.Product;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/products")
@Slf4j
public class ThymeleafController {
    private final RestClientController restClientController;

    public String showAllProducts(Model model) {
        model.addAttribute("products", restClientController.findAllProducts());
        return "products";
    }
}
