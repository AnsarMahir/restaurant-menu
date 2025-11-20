package com.toticone.restuarant_menu.dto;

import jakarta.annotation.Nullable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UtilityInfoDTO {
    private String name;
    @Nullable
    private String color;
    @Nullable
    private Integer size;
}
