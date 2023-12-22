package book.shop.orders.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface OrderItemRepository extends JpaRepository<OrderItemEntity, UUID>, JpaSpecificationExecutor<OrderItemEntity> {
}
