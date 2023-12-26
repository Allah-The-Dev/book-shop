package book.shop.carts;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Cart {

    private UUID cartId;

    private UUID userId;


    private List<CartItemEntity> cartItems;

    private float amount;

    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Timestamp deletedAt;

    public Cart(UUID cartId, UUID userId, List<CartItemEntity> cartItems,
                float amount, Timestamp createdAt, Timestamp updatedAt, Timestamp deletedAt) {
        this.cartId = cartId;
        this.userId = userId;
        this.cartItems = cartItems;
        this.amount = amount;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    public UUID getCartId() {
        return cartId;
    }

    public void setCartId(UUID cartId) {
        this.cartId = cartId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public List<CartItemEntity> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItemEntity> cartItems) {
        this.cartItems = cartItems;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Timestamp getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Timestamp deletedAt) {
        this.deletedAt = deletedAt;
    }

    public Cart() {
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cart cart = (Cart) o;

        if (Float.compare(amount, cart.amount) != 0) return false;
        if (!Objects.equals(cartId, cart.cartId)) return false;
        if (!Objects.equals(userId, cart.userId)) return false;
        if (!Objects.equals(cartItems, cart.cartItems)) return false;
        if (!Objects.equals(createdAt, cart.createdAt)) return false;
        if (!Objects.equals(updatedAt, cart.updatedAt)) return false;
        return Objects.equals(deletedAt, cart.deletedAt);
    }

    @Override
    public int hashCode() {
        int result = cartId != null ? cartId.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (cartItems != null ? cartItems.hashCode() : 0);
        result = 31 * result + (amount != 0.0f ? Float.floatToIntBits(amount) : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
        result = 31 * result + (deletedAt != null ? deletedAt.hashCode() : 0);
        return result;
    }
}
