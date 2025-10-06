package com.toticone.restuarant_menu.controller;

import com.toticone.restuarant_menu.dto.ExtraDTO;
import com.toticone.restuarant_menu.enums.ExtraTypes;
import com.toticone.restuarant_menu.service.ExtraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/extras")
@CrossOrigin(origins = "*")
public class ExtraController {

    @Autowired
    private ExtraService extraService;

    @GetMapping
    public ResponseEntity<List<ExtraDTO>> getAllExtras() {
        return ResponseEntity.ok(extraService.getAllExtras());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExtraDTO> getExtraById(@PathVariable int id) {
        return ResponseEntity.ok(extraService.getExtraById(id));
    }

    @PostMapping
    public ResponseEntity<ExtraDTO> createExtra(@RequestBody ExtraDTO extraDTO) {
        return ResponseEntity.ok(extraService.createExtra(extraDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExtraDTO> updateExtra(@PathVariable int id, @RequestBody ExtraDTO extraDTO) {
        return ResponseEntity.ok(extraService.updateExtra(id, extraDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExtra(@PathVariable int id) {
        extraService.deleteExtra(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<ExtraDTO>> getExtrasByType(@PathVariable ExtraTypes type) {
        return ResponseEntity.ok(extraService.getExtrasByType(type));
    }
}
