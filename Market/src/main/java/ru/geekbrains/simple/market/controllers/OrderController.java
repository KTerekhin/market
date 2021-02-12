package ru.geekbrains.simple.market.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.simple.market.beans.Cart;
import ru.geekbrains.simple.market.dto.OrderDto;
import ru.geekbrains.simple.market.exceptions_handling.MarketError;
import ru.geekbrains.simple.market.exceptions_handling.ResourceNotFoundException;
import ru.geekbrains.simple.market.model.Order;
import ru.geekbrains.simple.market.model.User;
import ru.geekbrains.simple.market.services.OrderService;
import ru.geekbrains.simple.market.services.UserService;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@Slf4j
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;
    private Cart cart;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrderFromCart(Principal principal, @RequestParam String address) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        orderService.createFromUserCart(user, address);
    }

//    @PostMapping("/confirm")
//    public ResponseEntity<?> confirmOrder(Principal principal, @RequestParam String address) {
//        Optional<User> optionalUser = userService.findByUsername(principal.getName());
//        if (optionalUser.isEmpty()) {
//            return new ResponseEntity<>(new MarketError(HttpStatus.UNAUTHORIZED.value(), "Error. Try to login again."), HttpStatus.UNAUTHORIZED);
//        }
//        User user = optionalUser.get();
//        Order order = new Order(user, cart, address);
//        return new ResponseEntity<>(orderService.saveOrder(order), HttpStatus.CREATED);
//    }

    @GetMapping
    public List<OrderDto> getCurrentUserOrders(Principal principal) {
        return orderService.findAllOrdersByOwnerName(principal.getName()).stream().map(OrderDto::new).collect(Collectors.toList());
    }
}
