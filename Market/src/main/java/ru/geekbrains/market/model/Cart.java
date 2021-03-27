package ru.geekbrains.market.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "carts")
@Data
@NoArgsConstructor
public class Cart {
    @Id
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    @Column(name = "id")
    private UUID id;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<CartItem> items;

    @Column(name = "price")
    private int price;

    public void add(CartItem cartItem) {
        this.items.add(cartItem);
        cartItem.setCart(this);
        recalculate();
    }

    public void decrementProductInCart(Long id) {
        for (CartItem cartItem : items) {
            if (cartItem.getProduct().getId().equals(id)) {
                cartItem.decrementQuantity();
                if (cartItem.isEmpty()) {
                    items.remove(cartItem);
                }
                recalculate();
                return;
            }
        }
    }

    public void recalculate() {
        price = 0;
        for (CartItem ci : items) {
            price += ci.getPrice();
        }
    }
}

