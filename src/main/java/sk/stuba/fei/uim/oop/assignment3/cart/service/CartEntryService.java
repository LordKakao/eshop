package sk.stuba.fei.uim.oop.assignment3.cart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.cart.entity.CartEntry;
import sk.stuba.fei.uim.oop.assignment3.cart.entity.CartEntryRepository;
import sk.stuba.fei.uim.oop.assignment3.product.entity.Product;

@Service
public class CartEntryService implements ICartEntryService {
    @Autowired
    CartEntryRepository repository;

    @Override
    public CartEntry create(Long amount, Product product) {
        CartEntry cartEntry = new CartEntry();
        cartEntry.setProduct(product);
        cartEntry.setAmount(amount);

        return this.repository.save(cartEntry);
    }

    @Override
    public CartEntry save(CartEntry cartEntry) {
        return this.repository.save(cartEntry);
    }
}
