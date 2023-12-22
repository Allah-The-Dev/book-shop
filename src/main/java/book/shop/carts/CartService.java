package book.shop.carts;

import book.shop.books.Book;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.Optional;
import java.util.UUID;


@Service
public class CartService {
    CartRepository cartRepository;

    ModelMapper modelMapper;

    @Autowired
    public CartService(CartRepository cartRepository, ModelMapper modelMapper) {
        this.cartRepository = cartRepository;
        this.modelMapper = modelMapper;
    }

    public Cart cart(UUID userID) {
        return modelMapper.map(cartRepository.findByUserId(userID), Cart.class);
    }
}
