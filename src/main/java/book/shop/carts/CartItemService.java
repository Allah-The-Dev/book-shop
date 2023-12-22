package book.shop.carts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.UUID;


@Service
public class CartItemService {
    CartItemRepository cartItemRepository;

    @Autowired
    public CartItemService(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    public void removeCartItem(UUID cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }
}
