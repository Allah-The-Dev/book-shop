package book.shop.carts;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CartItemServiceTest {

    @InjectMocks
    CartItemService cartItemService;
    @Mock
    CartItemRepository cartItemRepository;

    @BeforeEach
    public void setup() {
        cartItemService = new CartItemService(cartItemRepository);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void cartItemServiceShouldDeleteCartItemByCartItemId(){
        UUID cartItemId = new UUID(1L, 1L);

        doNothing().when(cartItemRepository).deleteById(cartItemId);

        CartItemService cartItemService = new CartItemService(cartItemRepository);

        cartItemService.removeCartItem(cartItemId);

        verify(cartItemRepository, times(1)).deleteById(cartItemId);

        assertDoesNotThrow(() -> cartItemService.removeCartItem(cartItemId));
    }
}
