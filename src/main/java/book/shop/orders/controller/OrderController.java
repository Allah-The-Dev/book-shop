package book.shop.orders.controller;

import book.shop.orders.service.OrderService;
import book.shop.orders.dto.OrderDetailsRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/orders")
    public ResponseEntity<String> purchase(@RequestBody OrderDetailsRequest orderDetailsDto) {
        if(orderService.purchase(orderDetailsDto) == true)
            return ResponseEntity.ok("Order is confirmed !!!");
        else
            return ResponseEntity.internalServerError().body("Order Failed !");
    }
}
