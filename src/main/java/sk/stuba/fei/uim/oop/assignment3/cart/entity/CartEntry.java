package sk.stuba.fei.uim.oop.assignment3.cart.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import sk.stuba.fei.uim.oop.assignment3.product.entity.Product;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class CartEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(targetEntity = Product.class)
    private Product product;

    private Long amount;
}
