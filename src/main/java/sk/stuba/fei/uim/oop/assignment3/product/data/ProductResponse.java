package sk.stuba.fei.uim.oop.assignment3.product.data;

import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.assignment3.product.entity.Product;

@Getter
@Setter
public class ProductResponse extends ProductRequest {
    private Long id;

    public ProductResponse(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.unit = product.getUnit();
        this.price = product.getPrice();
        this.amount = product.getAmount();
    }
}
