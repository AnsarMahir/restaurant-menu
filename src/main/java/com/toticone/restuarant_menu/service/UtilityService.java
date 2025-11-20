package com.toticone.restuarant_menu.service;

import com.toticone.restuarant_menu.dto.UtilityInfoDTO;

public interface UtilityService {
    UtilityInfoDTO getUtilityInfo(String name);

    String createUtility(UtilityInfoDTO dto);
}
