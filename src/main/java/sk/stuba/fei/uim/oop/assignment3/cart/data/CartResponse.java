package sk.stuba.fei.uim.oop.assignment3.cart.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sk.stuba.fei.uim.oop.assignment3.cart.entity.Cart;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartResponse {
    private Long id;
    private List<CartEntryReq> shoppingList;
    private boolean payed;

    public CartResponse(Cart shoppingCart) {
        this.id = shoppingCart.getId();
        this.payed = shoppingCart.isPayed();
        this.shoppingList = shoppingCart.getShoppingList().stream().map(CartEntryReq::new).collect(Collectors.toList());
    }
}
