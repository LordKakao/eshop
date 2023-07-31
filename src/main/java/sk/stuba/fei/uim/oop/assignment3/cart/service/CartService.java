package sk.stuba.fei.uim.oop.assignment3.cart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.cart.data.CartEntryReq;
import sk.stuba.fei.uim.oop.assignment3.cart.entity.Cart;
import sk.stuba.fei.uim.oop.assignment3.cart.entity.CartEntry;
import sk.stuba.fei.uim.oop.assignment3.cart.entity.CartRepository;
import sk.stuba.fei.uim.oop.assignment3.exception.BadRequestException;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.product.service.IProductService;

@Service
public class CartService implements ICartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private IProductService productService;

    @Autowired
    private ICartEntryService cartEntryService;

    @Override
    public Cart create() {
        return this.cartRepository.save(new Cart());
    }

    @Override
    public Cart getById(Long cartId) throws NotFoundException {
        return this.cartRepository.findById(cartId).orElseThrow(NotFoundException::new);
    }

    @Override
    public void delete(Long cartId) throws NotFoundException {
        try {
            this.cartRepository.deleteById(cartId);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException();
        }
    }

    @Override
    public Cart addToCart(Long cartId, CartEntryReq cartEntry) throws NotFoundException, BadRequestException {
        Cart cart = getById(cartId);

        if(cart.isPayed()) {
            throw new BadRequestException();
        }

        this.productService.removeAmount(cartEntry.getProductId(), cartEntry.getAmount());

        CartEntry existingEntry = null;
        for (CartEntry entry : cart.getShoppingList()) {
            if (entry.getProduct().getId().equals(cartEntry.getProductId())) {
                existingEntry = entry;
            }
        }

        if (existingEntry == null) {
            existingEntry = this.cartEntryService.create(cartEntry.getAmount(), this.productService.getProductById(cartEntry.getProductId()));
            cart.getShoppingList().add(existingEntry);
        } else {
            existingEntry.setAmount(existingEntry.getAmount() + cartEntry.getAmount());
        }

        this.cartEntryService.save(existingEntry);

        return this.cartRepository.save(cart);
    }

    @Override
    public double payForCart(Long cartId) throws NotFoundException, BadRequestException {
        Cart cart = getById(cartId);

        if(cart.isPayed()) {
            throw new BadRequestException();
        }

        cart.setPayed(true);
        this.cartRepository.save(cart);

        return cart.getShoppingList().stream().mapToDouble(item -> item.getAmount() * item.getProduct().getPrice()).sum();
    }
}
