package book.shop.carts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class CartItemController {


    @Autowired
    CartItemService cartItemService;

    @DeleteMapping(value = "cartItem/{cartItemId}")
    public ResponseEntity<Void> cart(@PathVariable UUID cartItemId) {
        cartItemService.removeCartItem(cartItemId);
        return ResponseEntity.noContent().build();
    }
}
