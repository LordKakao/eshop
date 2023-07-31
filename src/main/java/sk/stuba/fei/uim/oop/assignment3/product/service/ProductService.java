package sk.stuba.fei.uim.oop.assignment3.product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.exception.BadRequestException;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.product.data.ProductRequest;
import sk.stuba.fei.uim.oop.assignment3.product.entity.Product;
import sk.stuba.fei.uim.oop.assignment3.product.entity.ProductRepository;

import java.util.List;

@Service
public class ProductService implements IProductService {
    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return this.productRepository.findAll();
    }

    @Override
    public Product createProduct(Product product) {
        return this.productRepository.save(product);
    }

    @Override
    public Product getProductById(Long productId) throws NotFoundException {
        return this.productRepository.findById(productId).orElseThrow(NotFoundException::new);
    }

    @Override
    public Product updateProduct(Long productId, ProductRequest req) throws NotFoundException {
        Product product = this.productRepository.findById(productId).orElseThrow(NotFoundException::new);

        if(req.getName() != null) {
            product.setName(req.getName());
        }

        if(req.getDescription() != null) {
            product.setDescription(req.getDescription());
        }

        return this.productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long productId) throws NotFoundException {
        try {
            this.productRepository.deleteById(productId);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException();
        }
    }

    @Override
    public long getAmount(Long productId) throws NotFoundException {
        Product product = this.getProductById(productId);
        return product.getAmount();
    }

    @Override
    public long increaseAmount(Long productId, Long amount) throws NotFoundException {
        Product product = this.getProductById(productId);
        long newAmount = product.getAmount() + amount;
        product.setAmount(newAmount);

        return product.getAmount();
    }

    @Override
    public void removeAmount(Long productId, Long decrement) throws NotFoundException, BadRequestException {
        Product product = this.getProductById(productId);
        if (product.getAmount() < decrement) {
            throw new BadRequestException();
        }
        product.setAmount(product.getAmount() - decrement);
        this.productRepository.save(product);
    }
}
