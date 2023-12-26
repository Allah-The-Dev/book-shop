package book.shop.carts;

import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class CartItemService {
    CartItemRepository cartItemRepository;

    public CartItemService(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    public void removeCartItem(UUID cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }
}
