package ru.gb.angular.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.rest.service.dto.ProductDto;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
public class AngularController {
    private final RestClientController restClientController;

    @GetMapping("/products")
    public List<ProductDto> findAllProducts() {
        return restClientController.findAllProducts();
    }
}
