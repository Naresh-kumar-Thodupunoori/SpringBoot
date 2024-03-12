package dev.naresh.productcart.controllers;

import java.util.List;

import dev.naresh.productcart.models.Cart;
import dev.naresh.productcart.services.CartServiceImpl;

import org.springframework.web.bind.annotation.*;



@RestController
public class CartController {

    private CartServiceImpl CartService;

    public CartController(CartServiceImpl cartService) {
        this.CartService = cartService;
    }

    @GetMapping("/carts")
    public List<Cart> getAllCarts() {
        return CartService.getAllCarts();
    }

    
    @GetMapping("/carts/{id}")
    public Cart getCart(@PathVariable Long id) {
        return CartService.getCart(id);
    }


    @GetMapping("/carts/{startDate}/{endDate}")
    public List<Cart> getCartsByDateRange(@PathVariable String startDate, @PathVariable String endDate) {
        return CartService.getCartsByDateRange(startDate, endDate);
    }

    @GetMapping("/carts/user/{userId}")
    public List<Cart> getUserCart(@PathVariable Long userId) {
        return CartService.getUserCart(userId);
    }

    @PostMapping("/carts/add")
    public Cart addCart(@RequestBody Cart cart) {
        return CartService.addCart(cart);
    }

    @PutMapping("/carts/{id}")
    public Cart updateCart(@PathVariable Long id, @RequestBody Cart cart) {
        return CartService.updateCart(id, cart);
    }

    @DeleteMapping("/carts/{id}")
    public String deleteCart(@PathVariable Long id) {
        CartService.deleteCart(id);
        return "Cart: " + id + " deleted successfully";
    }
}
