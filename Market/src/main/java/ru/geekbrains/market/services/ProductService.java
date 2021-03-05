package ru.geekbrains.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.geekbrains.market.dto.ProductDto;
import ru.geekbrains.market.repositories.ProductRepository;
import ru.geekbrains.market.soap.products.ProductSoap;
import ru.geekbrains.market.model.Product;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Optional<Product> findProductById(Long id) {
        return productRepository.findById(id);
    }

    public Optional<ProductDto> findProductDtoById(Long id) {
        return productRepository.findById(id).map(ProductDto::new);
    }

    public Page<ProductDto> findAll(Specification<Product> spec, int page, int pageSize) {
        return productRepository.findAll(spec, PageRequest.of(page - 1, pageSize)).map(ProductDto::new);
    }

    public Product saveOrUpdate(Product product) {
        return productRepository.save(product);
    }

    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    public static final Function<Product, ProductSoap> functionEntityToSoap = p -> {
        ProductSoap productSoap = new ProductSoap();
        productSoap.setId(p.getId());
        productSoap.setTitle(p.getTitle());
        productSoap.setPrice(p.getPrice());
        productSoap.setCreatedAt(p.getCreatedAt());
        productSoap.setUpdatedAt(p.getUpdatedAt());
        return productSoap;
    };

    public ProductSoap getById(Long id) {
        return productRepository.findById(id).map(functionEntityToSoap).get();
    }

    public List<ProductSoap> getAllProducts() {
        return productRepository.findAll().stream().map(functionEntityToSoap).collect(Collectors.toList());
    }
}
