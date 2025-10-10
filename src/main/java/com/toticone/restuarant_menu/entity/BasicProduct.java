package com.toticone.restuarant_menu.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="basic_products")
public class BasicProduct {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    @NotBlank
    private String name;
    private String description;
    private String category;
    @NotNull
    private double price;
    private int type;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "product_extras",
            joinColumns = @JoinColumn(name="product_id"),
            inverseJoinColumns = @JoinColumn(name="extra_id")
    )
    private List<Extra> extras =  new ArrayList<>();

    public BasicProduct(String name, String description, double price, int type) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.type = type;
    }

    // Helper methods
    public void addExtra(Extra extra) {
        extras.add(extra);
        extra.getProducts().add(this);
    }

    public void removeExtra(Extra extra) {
        extras.remove(extra);
        extra.getProducts().remove(this);
    }

}
