package com.toticone.restuarant_menu.repository;

import com.toticone.restuarant_menu.dto.UtilityInfoDTO;
import com.toticone.restuarant_menu.entity.UtilityInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface UtilityInfoRepository extends JpaRepository<UtilityInfo,Integer> {
    UtilityInfo findByName(String name);
}
