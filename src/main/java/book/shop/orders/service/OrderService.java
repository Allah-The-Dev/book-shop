package book.shop.orders.service;


import book.shop.orders.dto.OrderDetailsRequest;
import book.shop.orders.dto.OrderItemsRequest;
import book.shop.orders.repository.OrderItemEntity;
import book.shop.orders.repository.OrdersEntity;
import book.shop.orders.repository.OrderItemRepository;
import book.shop.orders.repository.OrdersRepository;
import jakarta.transaction.Transactional;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
public class OrderService {

    private OrdersRepository ordersRepository;

    private OrderItemRepository orderItemRepository;


    public OrderService(OrdersRepository ordersRepository, OrderItemRepository orderItemRepository) {
        this.ordersRepository = ordersRepository;
        this.orderItemRepository = orderItemRepository;
    }

    @Transactional
    public boolean purchase(OrderDetailsRequest orderDetailsDto) {
        if(orderDetailsDto.orderItemsList().isEmpty()){
            return false;
        }
        OrdersEntity ordersEntity = new OrdersEntity();
        ordersEntity.setAddress(orderDetailsDto.address());
        ordersEntity.setPaymentMode(orderDetailsDto.paymentMode());
        ordersEntity.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        ordersEntity.setUserId(UUID.fromString(orderDetailsDto.userId()));
        ordersEntity.setTotalAmount(orderDetailsDto.totalAmount());

        OrdersEntity orderEntitySaved = ordersRepository.save(ordersEntity);
        if(ObjectUtils.isEmpty(orderEntitySaved)){
            return false;
        }
        List<OrderItemEntity> orderItemEntityList = new ArrayList<>();
        for (OrderItemsRequest item : orderDetailsDto.orderItemsList()) {
            OrderItemEntity orderItemEntity = new OrderItemEntity();
            orderItemEntity.setOrderId(orderEntitySaved.getOrderID());
            orderItemEntity.setBookId(item.bookId());
            orderItemEntity.setPrice(item.price());
            orderItemEntity.setQuantity(item.quantity());
            orderItemEntityList.add(orderItemEntity);
        }


        List<OrderItemEntity> orderSaved =  orderItemRepository.saveAll(orderItemEntityList);
        if(ObjectUtils.isEmpty(ObjectUtils.isEmpty(orderSaved))){
            return false;
        }
        return true;
        }
    }
