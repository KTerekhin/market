package ru.gb.thymeleaf.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import ru.gb.rest.service.model.Product;

import java.util.List;

@FeignClient("rest-service")
public interface RestClientController {
    @GetMapping("/products")
    List<Product> findAllProducts();
}
