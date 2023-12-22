package book.shop.orders;

import book.shop.orders.dto.OrderDetailsRequest;
import book.shop.orders.dto.OrderItemsRequest;
import book.shop.orders.repository.OrderItemEntity;
import book.shop.orders.repository.OrdersEntity;
import book.shop.orders.repository.OrderItemRepository;
import book.shop.orders.repository.OrdersRepository;
import book.shop.orders.service.OrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;


public class OrderServiceTest {

    private OrderService orderService;

    @Mock
    OrdersRepository ordersRepository;

    @Mock
    OrderItemRepository orderItemRepository;

    private UUID orderId = UUID.randomUUID();
    private int bookId = 123;
    private UUID userId = UUID.randomUUID();


    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        orderService = new OrderService(ordersRepository, orderItemRepository);

    }

    @Test
    public void shouldReturnTrueWhenOrderIsPlacedSuccessfully(){
        OrderDetailsRequest orderDetailsDto = getOrderDetails();
        when(ordersRepository.save(Mockito.any())).thenReturn(getOrderEntity());
        when(orderItemRepository.saveAll(Mockito.any())).thenReturn(getOrderItemEntityList());

        Assertions.assertTrue(orderService.purchase(orderDetailsDto));
    }

    @Test
    public void shouldReturnFalseWhenOrderFails(){
        OrderDetailsRequest orderDetailsDto = getOrderDetails();
        when(ordersRepository.save(Mockito.any())).thenReturn(null);
        when(orderItemRepository.saveAll(Mockito.any())).thenReturn(getOrderItemEntityList());

        Assertions.assertFalse(orderService.purchase(orderDetailsDto));
    }

    private OrderDetailsRequest getOrderDetails() {
        return new OrderDetailsRequest("Address1", userId.toString(),"COD",123.123F, getOrderItemsList());
    }

    private OrdersEntity getOrderEntity() {
        OrdersEntity ordersEntity = new OrdersEntity();
        ordersEntity.setOrderID(orderId);
        ordersEntity.setUserId(userId);
        ordersEntity.setTotalAmount(100F);
        ordersEntity.setAddress("Bengaluru Karnataka");
        ordersEntity.setPaymentMode("COD");
        ordersEntity.setCreatedAt(Timestamp.valueOf("2023-12-22 10:01:15"));
        return ordersEntity;
    }

    private List<OrderItemEntity> getOrderItemEntityList() {
        OrderItemEntity orderItemEntity = new OrderItemEntity();
        orderItemEntity.setOrderId(orderId);
        orderItemEntity.setBookId(bookId);
        orderItemEntity.setQuantity(10);
        orderItemEntity.setPrice(10F);
        return List.of(orderItemEntity);
    }

    private List<OrderItemsRequest> getOrderItemsList() {
        OrderItemsRequest orderItems = new OrderItemsRequest(bookId,10,10F);
        return List.of(orderItems);
    }
}
