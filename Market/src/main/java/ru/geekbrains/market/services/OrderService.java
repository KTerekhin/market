package ru.geekbrains.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.market.exceptions_handling.ResourceNotFoundException;
import ru.geekbrains.market.model.Cart;
import ru.geekbrains.market.repositories.OrderRepository;
import ru.geekbrains.market.model.Order;
import ru.geekbrains.market.model.User;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserService userService;
    private final CartService cartService;

    @Transactional
    public Order createFromUserCart(String username, UUID cartUuid, String address) {
        User user = userService.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Cart cart = cartService.findById(cartUuid).orElseThrow(() -> new ResourceNotFoundException("Cart not found"));
        Order order = new Order(cart, user, address);
        order = orderRepository.save(order);
        return order;
    }

    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    public List<Order> findAllOrdersByOwnerName(String username) {
        return orderRepository.findAllByOwnerUsername(username);
    }
}
