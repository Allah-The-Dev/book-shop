package book.shop.carts;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CartServiceTest {


    @InjectMocks
    CartService cartService;

    @Mock
    CartRepository cartRepository;

    @Autowired
    ModelMapper modelMapper;


    @BeforeEach
    public void setup() {
        ModelMapper modelMapper = new ModelMapper();
        cartService = new CartService(cartRepository, modelMapper);
        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void shouldGetShoppingCart() {

        UUID userID = new UUID(1L, 1L);

        when(cartRepository.findByUserId(userID)).thenReturn(Optional.of(createDummyCartEntity()));

        Cart cart = cartService.cart(userID);

        Cart expectedCart = createDummyCart();

        verify(cartRepository, times(1)).findByUserId(userID);

        assertEquals(cart.getUserId(), expectedCart.getUserId());
    }

    public CartEntity createDummyCartEntity() {

        // Add dummy CartItemEntity to the cartItems list
        CartItemEntity dummyCartItem = new CartItemEntity();
        dummyCartItem.setTotalPrice(10.0f);        // Replace with your item price dummy data
        dummyCartItem.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        dummyCartItem.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        dummyCartItem.setDeletedAt(null);         // Replace with your item deletedAt dummy data if needed

        return new CartEntity(new UUID(1L, 1L),
                new UUID(1L, 1L),
                List.of(dummyCartItem),
                0.0f,
                getTimestamp(),
                getTimestamp(),
                null);
    }

    public static Cart createDummyCart() {
        Cart cart = new Cart();

        // Set dummy data for fields
        cart.setCartId(new UUID(1L, 1L));
        cart.setUserId(new UUID(1L, 1L));  // Replace with your user ID dummy data
        cart.setAmount(0.0f); // Replace with your amount dummy data
        cart.setCreatedAt(getTimestamp());
        cart.setUpdatedAt(getTimestamp());
        cart.setDeletedAt(null);  // Replace with your deletedAt dummy data if needed

        // Add dummy CartItemEntity to the cartItems list
        CartItemEntity dummyCartItem = new CartItemEntity();

        dummyCartItem.setTotalPrice(10.0f);        // Replace with your item price dummy data
        dummyCartItem.setCreatedAt(getTimestamp());
        dummyCartItem.setUpdatedAt(getTimestamp());
        dummyCartItem.setDeletedAt(null);         // Replace with your item deletedAt dummy data if needed

        cart.setCartItems(List.of(dummyCartItem));
        return cart;
    }

    private static Timestamp getTimestamp() {
        return new Timestamp(Instant.parse("2023-01-01T12:00:00Z").toEpochMilli());
    }

}
