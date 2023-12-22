package book.shop.carts;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

interface CartItemRepository extends JpaRepository<CartItemEntity, UUID> {
}
