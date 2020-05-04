package com.fincity.example.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.fincity.example.dto.CarModelAttributes;
import com.fincity.example.dto.OperatorType;
import com.fincity.example.model.CarEntity;

public class CarSpecification {

    public static Specification<CarEntity> search(CarModelAttributes car) {
        return (root, query, CriteriaBuilder) -> {
            final List<Predicate> predicates = new ArrayList<>();
            if (car != null) {
                if (car.getName() != null) {
                    predicates.add(CriteriaBuilder.equal(root.get("name"), car.getName()));
                }

                if (car.getManufactureName() != null) {
                    predicates.add(CriteriaBuilder.equal(root.get("manufactureName"), car.getManufactureName()));
                }

                if (car.getManufacturingYear() != null) {
                    predicates.add(CriteriaBuilder.equal(root.get("manufacturingYear"), car.getManufacturingYear()));
                }

                if (car.getModel() != null) {
                    predicates.add(CriteriaBuilder.equal(root.get("model"), car.getModel()));
                }
               
                if (car.getColor() != null) {
                    predicates.add(CriteriaBuilder.equal(root.get("color"), car.getColor()));
                }
            }
            if (car.getOperatorType() != null && car.getOperatorType() == OperatorType.AND)
            	return CriteriaBuilder.and(predicates.stream().toArray(Predicate[]::new));
            else
            	return CriteriaBuilder.or(predicates.stream().toArray(Predicate[]::new));
        };
    }
}
