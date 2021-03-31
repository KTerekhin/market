package ru.geekbrains.market.tests.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import ru.geekbrains.market.model.Product;
import ru.geekbrains.market.repositories.ProductRepository;
import ru.geekbrains.market.services.ProductService;
import ru.geekbrains.market.services.UserService;

import java.util.Optional;

@SpringBootTest
@ActiveProfiles("test")
public class ProductServiceTest {
    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @MockBean
    private ProductRepository productRepository;

    @Test
    public void getProductByIdTest() {
        Product demoProduct = new Product();
        demoProduct.setTitle("test");
        demoProduct.setPrice(50);
        demoProduct.setId(21L);

        Mockito
                .doReturn(Optional.of(demoProduct))
                .when(productRepository)
                .findById(21L);

        Product p = productService.findProductById(21L).get();
        Mockito.verify(productRepository, Mockito.times(1)).findById(ArgumentMatchers.eq(21L));
        Assertions.assertEquals("test", p.getTitle());
    }

    @Test
    public void saveOrUpdateTest() {
        Product newProduct = new Product();
        newProduct.setId(22L);
        newProduct.setTitle("Coca-cola");
        newProduct.setPrice(60);

        Mockito
                .doReturn(newProduct)
                .when(productRepository)
                .save(newProduct);

        Product p = productService.saveOrUpdate(newProduct);
        Assertions.assertEquals(p, productRepository.save(newProduct));
    }


}
