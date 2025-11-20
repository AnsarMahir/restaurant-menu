package com.toticone.restuarant_menu.controller;

import com.toticone.restuarant_menu.dto.UtilityInfoDTO;
import com.toticone.restuarant_menu.entity.UtilityInfo;
import com.toticone.restuarant_menu.repository.UtilityInfoRepository;
import com.toticone.restuarant_menu.service.UtilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/api/utility")
public class UtilityController {
    @Autowired
    UtilityService utilityService;

    @PostMapping("/get")
    public ResponseEntity<UtilityInfoDTO> getUtilityInfo(@RequestBody String name) {
    return ResponseEntity.ok(utilityService.getUtilityInfo(name));
    }

    @PostMapping
    public ResponseEntity<String> createUtilityInfo(@RequestBody UtilityInfoDTO dto) {
        return ResponseEntity.ok(utilityService.createUtility(dto));
    }
}
