package com.toticone.restuarant_menu.service.impl;

import com.toticone.restuarant_menu.dto.ExtraDTO;
import com.toticone.restuarant_menu.entity.Extra;
import com.toticone.restuarant_menu.enums.ExtraTypes;
import com.toticone.restuarant_menu.repository.ExtraRepository;
import com.toticone.restuarant_menu.service.ExtraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExtraServiceImpl implements ExtraService {

    @Autowired
    private ExtraRepository extraRepository;

    @Override
    public List<ExtraDTO> getAllExtras() {
        return extraRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ExtraDTO getExtraById(int id) {
        Extra extra = extraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Extra not found with id: " + id));
        return convertToDTO(extra);
    }

    @Override
    public ExtraDTO createExtra(ExtraDTO extraDTO) {
        Extra extra = convertToEntity(extraDTO);
        Extra savedExtra = extraRepository.save(extra);
        return convertToDTO(savedExtra);
    }

    @Override
    public ExtraDTO updateExtra(int id, ExtraDTO extraDTO) {
        Extra existingExtra = extraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Extra not found with id: " + id));

        existingExtra.setName(extraDTO.getName());
        existingExtra.setType(extraDTO.getType());

        Extra updatedExtra = extraRepository.save(existingExtra);
        return convertToDTO(updatedExtra);
    }

    @Override
    public void deleteExtra(int id) {
        Extra extra = extraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Extra not found with id: " + id));
        extraRepository.delete(extra);
    }

    @Override
    public List<ExtraDTO> getExtrasByType(ExtraTypes type) {
        return extraRepository.findByType(type).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private ExtraDTO convertToDTO(Extra extra) {
        ExtraDTO dto = new ExtraDTO();
        dto.setId(extra.getId());
        dto.setName(extra.getName());
        dto.setType(extra.getType());
        return dto;
    }

    private Extra convertToEntity(ExtraDTO dto) {
        Extra extra = new Extra();
        extra.setName(dto.getName());
        extra.setType(dto.getType());
        return extra;
    }
}
