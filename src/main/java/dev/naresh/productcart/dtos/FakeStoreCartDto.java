package dev.naresh.productcart.dtos;

import java.util.List;

import dev.naresh.productcart.models.Product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreCartDto {
    private Long id;
    private Long userId;
    private String date;
    private List<Product> products;
}
