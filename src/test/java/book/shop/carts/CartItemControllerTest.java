package book.shop.carts;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

import static org.mockito.Mockito.doNothing;

@WebMvcTest(CartItemController.class)
@AutoConfigureMockMvc
public class CartItemControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CartItemService cartItemService;

    @Test
    public void shouldReturn200OkWhenCartItemIsDeleted() throws Exception {
        UUID cartItemId = UUID.randomUUID();

        doNothing().when(cartItemService).removeCartItem(cartItemId);

        mockMvc.perform(MockMvcRequestBuilders.delete("/cartItem/{cartItemId}", cartItemId))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}
