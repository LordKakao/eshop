package sk.stuba.fei.uim.oop.assignment3.cart.service;

import sk.stuba.fei.uim.oop.assignment3.cart.data.CartEntryReq;
import sk.stuba.fei.uim.oop.assignment3.cart.entity.Cart;
import sk.stuba.fei.uim.oop.assignment3.exception.BadRequestException;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;

public interface ICartService {
    Cart create();

    Cart getById(Long cartId) throws NotFoundException;

    void delete(Long cartId) throws NotFoundException;

    Cart addToCart(Long cartId, CartEntryReq cartEntry) throws NotFoundException, BadRequestException;

    double payForCart(Long cartId) throws NotFoundException, BadRequestException;
}
