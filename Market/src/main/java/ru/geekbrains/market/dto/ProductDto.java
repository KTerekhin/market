package ru.geekbrains.market.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.market.model.Product;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class ProductDto implements Serializable {
    private Long id;
    private String title;
    private int price;

    public ProductDto(Product p) {
        this.id = p.getId();
        this.title = p.getTitle();
        this.price = p.getPrice();
    }
}
