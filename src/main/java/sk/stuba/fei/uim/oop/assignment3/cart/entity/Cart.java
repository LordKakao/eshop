package sk.stuba.fei.uim.oop.assignment3.cart.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(targetEntity = CartEntry.class, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<CartEntry> shoppingList;

    private boolean payed;

    public Cart() {
        this.shoppingList = new ArrayList<>();
        this.payed = false;
    }
}
