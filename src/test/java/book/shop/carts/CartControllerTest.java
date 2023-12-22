package book.shop.carts;

import book.shop.books.BookController;
import book.shop.carts.models.CartRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.swing.text.html.CSS;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;


@WebMvcTest(CartController.class)
@AutoConfigureMockMvc
public class CartControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CartService cartService;

    @Test
    public void shouldGetCartByUserId() throws Exception {
        UUID userId = new UUID(1L, 1L);

        when(cartService.cart(userId)).thenReturn(getCartResponse());

        mockMvc.perform(MockMvcRequestBuilders.post("/cart")
                        .contentType("application/json") // Set content type to JSON
                        .content("{\"userId\":\""+ userId +"\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.userId")
                        .value(userId.toString())); // Ensure userId is a String in JSON
    }


    private CartRequest getCartRequest() {
        return new CartRequest(new UUID(1L, 1L));
    }

    public static Cart getCartResponse() {
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
