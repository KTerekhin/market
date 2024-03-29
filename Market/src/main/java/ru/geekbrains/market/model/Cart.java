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

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> items;

    @Column(name = "price")
    private int price;

    @OneToOne
    @JoinColumn(name = "owner_id")
    private User user;

    public void add(CartItem cartItem) {
        for (CartItem ci : this.items) {
            if (ci.getProduct().getId().equals(cartItem.getProduct().getId())) {
                ci.incrementQuantity(cartItem.getQuantity());
                recalculate();
                return;
            }
        }
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

    public void clear() {
        for (CartItem ci : items) {
            ci.setCart(null);
        }
        items.clear();
        recalculate();
    }

    public CartItem getItemByProductId(Long productId) {
        for (CartItem ci : items) {
            if (ci.getProduct().getId().equals(productId)) {
                return ci;
            }
        }
        return null;
    }

    public void merge(Cart another) {
        for (CartItem ci : another.items) {
            add(ci);
        }
    }
}
