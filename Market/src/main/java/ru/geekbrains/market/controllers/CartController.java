package ru.geekbrains.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.market.dto.CartDto;
import ru.geekbrains.market.exceptions_handling.ResourceNotFoundException;
import ru.geekbrains.market.model.Cart;
import ru.geekbrains.market.services.CartService;

import java.security.Principal;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @PostMapping
    public UUID createNewCart(Principal principal) {
        if (principal == null) {
            return cartService.getCartForUser(null, null);
        }
        return cartService.getCartForUser(principal.getName(), null);
    }

    @GetMapping("/{uuid}")
    public CartDto getCurrentCart(@PathVariable UUID uuid) {
        Cart cart = cartService.findById(uuid).orElseThrow(() -> new ResourceNotFoundException("Unable to find cart with id: " + uuid));
        return new CartDto(cart);
    }

    @PostMapping("/add")
    public void addProductToCart(@RequestParam UUID uuid, @RequestParam(name = "product_id") Long productId) {
        cartService.addToCart(uuid, productId);
    }

    @GetMapping("/dec")
    public void decrementProductFromCart(@RequestParam UUID uuid, @RequestParam(name = "product_id") Long productId) {
        cartService.decrementProductFromCart(uuid, productId);
    }

    @PostMapping("/clear")
    public void clearCart(@RequestParam UUID uuid) {
        cartService.clearCart(uuid);
    }
}

