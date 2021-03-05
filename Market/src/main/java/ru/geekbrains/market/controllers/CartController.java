package ru.geekbrains.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.market.dto.CartDto;
import ru.geekbrains.market.beans.Cart;

@RestController
@RequestMapping("api/v1/cart")
@RequiredArgsConstructor
public class CartController {
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
}
