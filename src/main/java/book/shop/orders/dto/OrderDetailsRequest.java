package book.shop.orders.dto;

import java.util.List;
import java.util.UUID;

public record OrderDetailsRequest(String address, String userId, String paymentMode, float totalAmount, List<OrderItemsRequest> orderItemsList) {

}
