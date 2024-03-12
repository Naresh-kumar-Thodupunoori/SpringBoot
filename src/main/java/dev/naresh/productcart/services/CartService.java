package dev.naresh.productcart.services;

import dev.naresh.productcart.models.Cart;
import java.util.List;

public interface CartService {

    public List<Cart> getAllCarts();

    public Cart getCart(Long id);

    public List<Cart> getCartsByDateRange(String startDate, String endDate);

    public List<Cart> getUserCart(Long userId);

    public Cart addCart(Cart cart);

    public Cart updateCart(Long id, Cart cart);

    public String deleteCart(Long id);

}
