package book.shop.orders.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface OrdersRepository extends JpaRepository<OrdersEntity, UUID>, JpaSpecificationExecutor<OrdersEntity> {
}
