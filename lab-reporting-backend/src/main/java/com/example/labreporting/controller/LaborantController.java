package com.example.labreporting.controller;

import com.example.labreporting.dto.LaborantDTO;
import com.example.labreporting.service.LaborantService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/api/v1/laborants")
@Validated
public class LaborantController {

    private final LaborantService laborantService;

    public LaborantController(LaborantService laborantService) {
        this.laborantService = laborantService;
    }

    @PostMapping
    public ResponseEntity<LaborantDTO> createLaborant(@Valid @RequestBody LaborantDTO laborantDTO){
        LaborantDTO createdLaborant = laborantService.createLaborant(laborantDTO);
        return ResponseEntity.ok().body(createdLaborant);
    }

    @GetMapping
    public ResponseEntity<List<LaborantDTO>> getAllLaborants() {
        List<LaborantDTO> laborants = laborantService.getAllLaborants();
        return ResponseEntity.ok(laborants);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLaborant(@PathVariable("id") UUID id){
        laborantService.deleteLaborant(id);
        return new ResponseEntity<>(NO_CONTENT);
    }

    @GetMapping("/search")
    public ResponseEntity<List<LaborantDTO>> searchLaborants(@RequestParam(required = false) String searchQuery){
        List<LaborantDTO> laborants = laborantService.searchLaborants(searchQuery);
        return ResponseEntity.ok().body(laborants);
    }

}
