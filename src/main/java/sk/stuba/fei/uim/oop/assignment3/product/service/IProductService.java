package sk.stuba.fei.uim.oop.assignment3.product.service;

import sk.stuba.fei.uim.oop.assignment3.exception.BadRequestException;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.product.data.ProductRequest;
import sk.stuba.fei.uim.oop.assignment3.product.entity.Product;

import java.util.List;

public interface IProductService {
    List<Product> getAllProducts();
    Product createProduct(Product product);

    Product getProductById(Long productId) throws NotFoundException;

    Product updateProduct(Long productId, ProductRequest req) throws NotFoundException;

    void deleteProduct(Long productId) throws NotFoundException;

    long getAmount(Long productId) throws NotFoundException;

    long increaseAmount(Long productId, Long amount) throws NotFoundException;

    void removeAmount(Long productId, Long decrement) throws NotFoundException, BadRequestException;
}
