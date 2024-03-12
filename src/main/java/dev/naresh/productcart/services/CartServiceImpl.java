package dev.naresh.productcart.services;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import dev.naresh.productcart.dtos.FakeStoreCartDto;
import dev.naresh.productcart.models.Cart;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CartServiceImpl implements CartService {

    private final RestTemplate restTemplate = new RestTemplate();

    private static final String url = "https://fakestoreapi.com/carts";

    public List<Cart> getAllCarts() {
        return Arrays.stream(restTemplate.getForObject(url, FakeStoreCartDto[].class))
                .map(this::mapDtoToCart)
                .collect(Collectors.toList());
    }

    public Cart getCart(Long id) {
        return mapDtoToCart(restTemplate.getForObject(url + "/" + id, FakeStoreCartDto.class));
    }

    public List<Cart> getCartsByDateRange(String startDate, String endDate) {
        return Arrays.stream(restTemplate.getForObject(url + "?startdate=" + startDate + "&enddate=" + endDate, FakeStoreCartDto[].class))
                .map(this::mapDtoToCart)
                .collect(Collectors.toList());
    }

    public List<Cart> getUserCart(Long userId) {
        return Arrays.stream(restTemplate.getForObject(url + "/user/" + userId, FakeStoreCartDto[].class))
                .map(this::mapDtoToCart)
                .collect(Collectors.toList());
    }

    public Cart addCart(Cart cart) {
        restTemplate.postForObject(url, mapCartToDto(cart), FakeStoreCartDto.class);
        System.out.println("Cart created!!");
        return cart;
    }

    public Cart updateCart(Long id, Cart cart) {
        restTemplate.put(url + "/" + id, mapCartToDto(cart));
        System.out.println("Cart updated!!");
        return cart;
    }

    public String deleteCart(Long id) {
        restTemplate.delete(url + "/" + id);
        System.out.println("Cart deleted!!");
        return "Cart deleted successfully";
    }


    private Cart mapDtoToCart(FakeStoreCartDto dto) {
        Cart cart = new Cart();
        cart.setId(dto.getId());
        cart.setUserId(dto.getUserId());
        cart.setDate(dto.getDate());
        cart.setProducts(dto.getProducts());

        return cart;
    }

    private FakeStoreCartDto mapCartToDto(Cart cart) {
        FakeStoreCartDto storeCart = new FakeStoreCartDto();
        storeCart.setId(cart.getId());
        storeCart.setUserId(cart.getUserId());
        storeCart.setDate(cart.getDate());
        storeCart.setProducts(cart.getProducts());

        return storeCart;
    }

}
