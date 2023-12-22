package book.shop.carts;


import book.shop.books.Books;
import book.shop.carts.models.CartRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CartController {


    @Autowired
    CartService cartService;

    @PostMapping(value = "cart")
    public ResponseEntity<Cart> cart(@RequestBody CartRequest cartRequest) {

        return ResponseEntity.ok(cartService.cart(cartRequest.userId()));
    }
}
