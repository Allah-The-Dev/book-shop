package book.shop.orders.dto;

import java.util.UUID;

public record  OrderItemsRequest(long bookId, int quantity, float price) {

}
