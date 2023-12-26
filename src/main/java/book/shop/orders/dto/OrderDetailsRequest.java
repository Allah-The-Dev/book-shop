package book.shop.orders.dto;

import java.util.List;

public record OrderDetailsRequest(String address, String userId, String paymentMode, float totalAmount, List<OrderItemsRequest> orderItemsList) {

}
