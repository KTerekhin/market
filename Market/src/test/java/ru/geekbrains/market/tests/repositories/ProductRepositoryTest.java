package ru.geekbrains.market.tests.repositories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import ru.geekbrains.market.model.Product;
import ru.geekbrains.market.repositories.ProductRepository;

import java.util.List;

@DataJpaTest
@ActiveProfiles("test")
public class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;

    @Test
    public void initDbTest() {
        List<Product> countList = productRepository.findAll();
        Assertions.assertEquals(20, countList.size());
    }

    @Test
    public void deleteProductFromDbTest() {
        productRepository.deleteById(1L);
        List<Product> products = productRepository.findAll();
        Assertions.assertEquals(19, products.size());
    }

    @Test
    public void getTitleByIdTest() {
        String product = productRepository.findById(15L).get().getTitle();
        Assertions.assertEquals("Sausage", product);
    }
}
