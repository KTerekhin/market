package ru.geekbrains.simple.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.simple.market.beans.Cart;
import ru.geekbrains.simple.market.dto.OrderDto;
import ru.geekbrains.simple.market.model.Order;
import ru.geekbrains.simple.market.model.User;
import ru.geekbrains.simple.market.repositories.OrderRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final Cart cart;

    public void createFromUserCart(User user, String address) {
        Order order = new Order(user, cart, address);
        order = orderRepository.save(order);
        cart.clear();
    }

    public List<Order> findAllOrdersByOwnerName(String username) {
        return orderRepository.findAllByOwnerUsername(username);
    }
}
