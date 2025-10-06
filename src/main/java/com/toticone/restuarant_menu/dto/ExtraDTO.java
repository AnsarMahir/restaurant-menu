package com.toticone.restuarant_menu.dto;

import com.toticone.restuarant_menu.enums.ExtraTypes;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExtraDTO {
    private int id;
    private String name;
    private ExtraTypes type;
}