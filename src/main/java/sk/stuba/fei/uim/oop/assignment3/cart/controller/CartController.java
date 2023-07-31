package sk.stuba.fei.uim.oop.assignment3.cart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.stuba.fei.uim.oop.assignment3.cart.data.CartEntryReq;
import sk.stuba.fei.uim.oop.assignment3.cart.data.CartResponse;
import sk.stuba.fei.uim.oop.assignment3.cart.service.ICartService;
import sk.stuba.fei.uim.oop.assignment3.exception.BadRequestException;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private ICartService cartService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CartResponse> createCart() {
        return new ResponseEntity<>(new CartResponse(cartService.create()), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CartResponse getCart(@PathVariable("id") Long cartId) throws NotFoundException {
        return new CartResponse(cartService.getById(cartId));
    }

    @DeleteMapping(value = "/{id}")
    public void deleteCart(@PathVariable("id") Long cartId) throws NotFoundException {
        cartService.delete(cartId);
    }

    @PostMapping(value = "/{id}/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public CartResponse addToCart(@PathVariable("id") Long cartId, @RequestBody CartEntryReq req) throws NotFoundException, BadRequestException {
        return new CartResponse(cartService.addToCart(cartId, req));
    }

    @GetMapping(value = "/{id}/pay", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> getAuthor(@PathVariable("id") Long cartId) throws NotFoundException, BadRequestException {
        return new ResponseEntity<>(String.valueOf(cartService.payForCart(cartId)), HttpStatus.OK);
    }
}
