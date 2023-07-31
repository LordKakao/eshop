package sk.stuba.fei.uim.oop.assignment3.product.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest extends Amount {
    protected String name;
    protected String description;
    protected String unit;
    protected double price;
}
