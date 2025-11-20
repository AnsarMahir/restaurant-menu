package com.toticone.restuarant_menu.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryChangeRequest {
    private String categoryName;
    private String newCategoryName;
}
