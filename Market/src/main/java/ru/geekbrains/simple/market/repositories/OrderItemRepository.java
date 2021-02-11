package ru.geekbrains.simple.market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.geekbrains.simple.market.model.OrderItem;
import ru.geekbrains.simple.market.model.Product;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>, JpaSpecificationExecutor<Product> {
}
