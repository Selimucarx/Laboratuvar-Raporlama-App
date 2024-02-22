package com.example.labreporting.service;

import com.example.labreporting.dto.LaborantDTO;
import com.example.labreporting.mapper.LaborantMapper;
import com.example.labreporting.model.Laborant;
import com.example.labreporting.repository.LaborantRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class LaborantService {



    private final LaborantRepository laborantRepository;

    private final LaborantMapper laborantMapper;

    public LaborantService(LaborantRepository laborantRepository, LaborantMapper laborantMapper) {
        this.laborantRepository = laborantRepository;
        this.laborantMapper = laborantMapper;
    }

    public LaborantDTO createLaborant(LaborantDTO laborantDTO) {
        Laborant newLaborant = laborantMapper.laborantDTOToLaborant(laborantDTO);
        Laborant savedLaborant = laborantRepository.save(newLaborant);
        return laborantMapper.laborantToLaborantDTO(savedLaborant);
    }


    public List<LaborantDTO> getAllLaborants() {
        return laborantRepository.findAll().stream()
                .map(laborantMapper::laborantToLaborantDTO)
                .collect(Collectors.toList());
    }

    public List<LaborantDTO> searchLaborants(String searchQuery) {
        Specification<Laborant> spec = LaborantSpecifications.withDynamicQuery(searchQuery);
        List<Laborant> laborants = laborantRepository.findAll(spec);
        return laborants.stream().map(laborantMapper::laborantToLaborantDTO).collect(Collectors.toList());
    }

    public void deleteLaborant(UUID id) {
        Optional<Laborant> optionalLaborant = laborantRepository.findById(id);
        if (optionalLaborant.isEmpty()) {
            throw new RuntimeException("Duzelcemburayi" + id);
        }
        laborantRepository.deleteById(id);
    }


}
