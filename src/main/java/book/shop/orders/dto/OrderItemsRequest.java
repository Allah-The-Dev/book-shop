package book.shop.orders.dto;

public record  OrderItemsRequest(long bookId, int quantity, float price) {

}
