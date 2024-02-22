package com.example.labreporting.service;

import com.example.labreporting.model.Report;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public class ReportSpecifications {


    public static Specification<Report> withDynamicQuery(String searchQuery) {
        return (root, query, cb) -> {
            if (!StringUtils.hasText(searchQuery)) {
                return cb.conjunction();
            }

            String likePattern = "%" + searchQuery.toLowerCase() + "%";

            Predicate fileNumberPredicate = cb.like(cb.lower(root.get("fileNumber")), likePattern);
            Predicate patientFirstNamePredicate = cb.like(cb.lower(root.get("patientFirstName")), likePattern);
            Predicate patientLastNamePredicate = cb.like(cb.lower(root.get("patientLastName")), likePattern);
            Predicate nationalNumberPredicate = cb.like(cb.lower(root.get("nationalNumber")), likePattern);
            Predicate diagnosisTitlePredicate = cb.like(cb.lower(root.get("diagnosisTitle")), likePattern);

            return cb.or(
                    fileNumberPredicate,
                    patientFirstNamePredicate,
                    patientLastNamePredicate,
                    nationalNumberPredicate,
                    diagnosisTitlePredicate
            );
        };
    }

}
