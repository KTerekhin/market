package ru.geekbrains.simple.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.simple.market.beans.Cart;
import ru.geekbrains.simple.market.dto.CartDto;
import ru.geekbrains.simple.market.model.User;
import ru.geekbrains.simple.market.services.UserService;

import java.security.Principal;

@RestController
@RequestMapping("api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final UserService userService;
    private final Cart cart;

    @GetMapping
    public CartDto showCart (){
        return new CartDto(cart);
    }

    @GetMapping("/add/{id}")
    public void addProductIntoCart(@PathVariable Long id) {
        cart.addProductToCart(id);
    }

    @GetMapping("/inc/{id}")
    public void incrementProductIntoCart(@PathVariable Long id) {
        cart.addProductToCart(id);
    }

    @GetMapping("/dec/{id}")
    public void decrementProductFromCart(@PathVariable Long id) {
        cart.decrementProductInCart(id);
    }

    @GetMapping("/clear")
    public void clearCart() {
        cart.clear();
    }

    @GetMapping("/checkout")
    public void checkout(Principal principal) {
        User user = userService.findByUsername(principal.getName()).get();
        cart.checkout(user);
    }
}
