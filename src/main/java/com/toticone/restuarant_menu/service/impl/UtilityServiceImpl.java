package com.toticone.restuarant_menu.service.impl;

import com.toticone.restuarant_menu.dto.UtilityInfoDTO;
import com.toticone.restuarant_menu.entity.UtilityInfo;
import com.toticone.restuarant_menu.repository.UtilityInfoRepository;
import com.toticone.restuarant_menu.service.UtilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UtilityServiceImpl implements UtilityService {
    @Autowired
    private UtilityInfoRepository utilityInfoRepository;

    @Override
    public UtilityInfoDTO getUtilityInfo(String name) {
    UtilityInfo utilityInfo = utilityInfoRepository.findByName(name);
    if  (utilityInfo == null) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Utility not found");
    }
    return toDTO(utilityInfo);
    }

    @Override
    public String createUtility(UtilityInfoDTO dto) {
        UtilityInfo utilityInfo = toEntity(dto);
        utilityInfoRepository.save(utilityInfo);
        return "Utility created successfully";
    }

    @Override
    public String updateUtility(UtilityInfoDTO dto){
        UtilityInfo utilityInfo = utilityInfoRepository.findByName(dto.getName());
        if (utilityInfo == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Utility not found");
        }
        if (dto.getSize() != null) {
            utilityInfo.setSize(dto.getSize());
        }
        if (dto.getColor() != null) {
            utilityInfo.setColor(dto.getColor());
        }
        utilityInfoRepository.save(utilityInfo);
        return "Utility updated successfully";
    }

    private UtilityInfo toEntity(UtilityInfoDTO dto) {
        UtilityInfo utilityInfo = new UtilityInfo();
        utilityInfo.setName(dto.getName());
        utilityInfo.setColor(dto.getColor());
        utilityInfo.setSize(dto.getSize());
        utilityInfoRepository.save(utilityInfo);
        return utilityInfo;
    }

    private UtilityInfoDTO toDTO(UtilityInfo utilityInfo) {
        UtilityInfoDTO dto = new UtilityInfoDTO();
        dto.setName(utilityInfo.getName());
        dto.setColor(utilityInfo.getColor());
        dto.setSize(utilityInfo.getSize());
        return dto;
    }
}
