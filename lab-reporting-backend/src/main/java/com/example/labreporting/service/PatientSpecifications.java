package com.example.labreporting.service;

import com.example.labreporting.model.Patient;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public class PatientSpecifications {

    public static Specification<Patient> withDynamicQuery(String searchQuery) {
        return (root, query, cb) -> {
            if (!StringUtils.hasText(searchQuery)) {
                return cb.conjunction();
            }

            String likePattern = "%" + searchQuery.toLowerCase() + "%";

            Predicate firstNamePredicate = cb.like(cb.lower(root.get("firstName")), likePattern);
            Predicate lastNamePredicate = cb.like(cb.lower(root.get("lastName")), likePattern);
            Predicate nationalNumberPredicate = cb.like(cb.lower(root.get("nationalNumber")), likePattern);

            return cb.or(firstNamePredicate, lastNamePredicate, nationalNumberPredicate);
        };
    }

}
