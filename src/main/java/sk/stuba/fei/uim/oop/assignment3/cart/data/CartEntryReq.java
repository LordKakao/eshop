package sk.stuba.fei.uim.oop.assignment3.cart.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sk.stuba.fei.uim.oop.assignment3.cart.entity.CartEntry;

@Getter
@Setter
@NoArgsConstructor
public class CartEntryReq {
    private Long productId;
    private Long amount;

    public CartEntryReq(CartEntry cartEntry) {
        this.productId = cartEntry.getProduct().getId();
        this.amount = cartEntry.getAmount();
    }
}
