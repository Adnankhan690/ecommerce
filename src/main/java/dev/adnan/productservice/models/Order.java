package dev.adnan.productservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

import java.util.List;

@Entity(name = "orders")
//@Entity(name = "order)
// Remember this error The error message indicates a syntax error in your SQL statement when
// trying to create a table named "order." The issue is likely caused by using a reserved keyword, "order,"
// which is also a MySQL keyword.
public class Order extends BaseModel {
    @ManyToMany
    @JoinTable(
            name = "Product_Order",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> product;
}
