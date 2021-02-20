package ru.geekbrains.simple.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.simple.market.beans.Cart;
import ru.geekbrains.simple.market.model.Order;
import ru.geekbrains.simple.market.model.User;
import ru.geekbrains.simple.market.repositories.OrderRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final Cart cart;

    public Order createFromUserCart(User user, String address) {
        Order order = new Order(cart, user, address);
        order = orderRepository.save(order);
        cart.clear();
        return order;
    }

    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    public List<Order> findAllOrdersByOwnerName(String username) {
        return orderRepository.findAllByOwnerUsername(username);
    }
}
