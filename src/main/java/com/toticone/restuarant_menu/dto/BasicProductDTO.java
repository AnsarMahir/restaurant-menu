package com.toticone.restuarant_menu.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class BasicProductDTO {
    private int id;
    private String name;
    private String category;
    private String description;
    private double price;
    private int type;
    private List<ExtraDTO> extras;
}