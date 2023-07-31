package sk.stuba.fei.uim.oop.assignment3.cart.service;

import sk.stuba.fei.uim.oop.assignment3.cart.entity.CartEntry;
import sk.stuba.fei.uim.oop.assignment3.product.entity.Product;

public interface ICartEntryService {
    CartEntry create(Long amount, Product product);

    CartEntry save(CartEntry cartEntry);
}
