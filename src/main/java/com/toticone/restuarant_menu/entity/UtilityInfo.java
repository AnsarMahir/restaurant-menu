package com.toticone.restuarant_menu.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.Nullable;

@Getter
@Setter
@Entity
@Table(name = "utility_information")
public class UtilityInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int  id;

    private String name;
    @Nullable
    private String color;
    @Nullable
    private Integer size;
}
