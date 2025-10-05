package com.toticone.restuarant_menu.entity;

import com.toticone.restuarant_menu.enums.ExtraTypes;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@Entity
@Table(name = "extras")
public class Extra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Enumerated(EnumType.STRING)
    private ExtraTypes type;

    @ManyToMany(mappedBy = "extras", fetch = FetchType.LAZY)
    private List<BasicProduct> products = new ArrayList<>();

    public Extra(String name, ExtraTypes type) {
        this.name = name;
        this.type = type;
    }

    public Extra(){}
}
