package book.shop.orders;

import book.shop.orders.controller.OrderController;
import book.shop.orders.dto.OrderDetailsRequest;
import book.shop.orders.dto.OrderItemsRequest;
import book.shop.orders.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
@AutoConfigureMockMvc
public class OrderControllerTest {

    @MockBean
    private OrderService orderService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private String orderId = "ORDERID";
    private long bookId = 123;

    @Test
    public void returnsStatus200WhenOrderIsConfirmed() throws Exception {
    OrderDetailsRequest order = getOrderDetails();
        when(orderService.purchase(Mockito.any())).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.post("/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(order))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void returnsStatus500WhenOrderFails() throws Exception {
        OrderDetailsRequest order = getOrderDetails();
        when(orderService.purchase(Mockito.any())).thenReturn(false);

        mockMvc.perform(MockMvcRequestBuilders.post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(order))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
    }

    private OrderDetailsRequest getOrderDetails() {
        return new OrderDetailsRequest("Address1", orderId,"COD",123.123F, getOrderItemList());
    }

    private List<OrderItemsRequest> getOrderItemList(){
        OrderItemsRequest orderItems = new OrderItemsRequest(bookId, 10, 100);
        return  List.of(orderItems);
    }


}
