package com.toticone.restuarant_menu.service;

import com.toticone.restuarant_menu.dto.ExtraDTO;
import com.toticone.restuarant_menu.enums.ExtraTypes;

import java.util.List;

public interface ExtraService {
    List<ExtraDTO> getAllExtras();
    ExtraDTO getExtraById(int id);
    ExtraDTO createExtra(ExtraDTO extraDTO);
    ExtraDTO updateExtra(int id, ExtraDTO extraDTO);
    void deleteExtra(int id);
    List<ExtraDTO> getExtrasByType(ExtraTypes type);
}
