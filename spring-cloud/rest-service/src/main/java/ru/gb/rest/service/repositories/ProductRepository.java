package ru.gb.rest.service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.rest.service.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
