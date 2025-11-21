package com.toticone.restuarant_menu.service;

import com.toticone.restuarant_menu.dto.UtilityInfoDTO;
import com.toticone.restuarant_menu.dto.UtilityInfoRequest;

public interface UtilityService {
    UtilityInfoDTO getUtilityInfo(String name);
    String updateUtility(UtilityInfoDTO dto);
    String createUtility(UtilityInfoDTO dto);
}
