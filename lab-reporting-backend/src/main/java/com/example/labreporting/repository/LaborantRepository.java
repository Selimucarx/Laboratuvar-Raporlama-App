package com.example.labreporting.repository;

import com.example.labreporting.model.Laborant;
import com.example.labreporting.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LaborantRepository extends JpaRepository<Laborant, UUID>, JpaSpecificationExecutor<Laborant> {

}
