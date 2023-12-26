package book.shop.carts;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class CartService {
    CartRepository cartRepository;

    ModelMapper modelMapper;

    public CartService(CartRepository cartRepository, ModelMapper modelMapper) {
        this.cartRepository = cartRepository;
        this.modelMapper = modelMapper;
    }

    public Cart cart(UUID userID) {
        return modelMapper.map(cartRepository.findByUserId(userID), Cart.class);
    }
}
