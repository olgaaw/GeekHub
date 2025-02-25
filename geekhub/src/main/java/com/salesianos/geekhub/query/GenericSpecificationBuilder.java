package com.salesianos.geekhub.query;

import com.salesianos.geekhub.model.Interest;
import com.salesianos.geekhub.util.SearchCriteria;
import jakarta.persistence.criteria.*;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Log
@AllArgsConstructor
public abstract class GenericSpecificationBuilder<U> {

    private List<SearchCriteria> params;


    public Specification<U> build() {
        if (params.size() == 0) {
            return null;
        }

        log.info("Adding first specification " + params.get(0));
        Specification<U> result = build(params.get(0));
        log.info("Specification: " + result.toString());

        for(int i = 1; i < params.size(); i++) {
            log.info("Adding new specification " + params.get(i));
            result = result.and(build(params.get(i)));
            log.info(result.toString());
            log.info("Specification: " + result.toString());
        }

        log.info("Final Specification: " + result.toString());


        return result;
    }

    private Specification<U> build(SearchCriteria criteria) {
        return (Root<U> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            if (criteria.key().equals("cp")) {
                return builder.equal(root.get("cp"), criteria.value());
            }

            if (criteria.key().equals("age")) {
                int age = (int) criteria.value();
                LocalDate today = LocalDate.now();
                LocalDate limitDate = today.minusYears(age);
                Date limitDateConverted = Date.from(limitDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

                if (criteria.operation().equals(">")) {
                    return builder.lessThanOrEqualTo(root.get("birthday"), limitDateConverted);
                } else {
                    return builder.greaterThanOrEqualTo(root.get("birthday"), limitDateConverted);
                }
            }


            if (criteria.key().equals("interests")) {
                Join<U, Interest> interestsJoin = root.join("interests");

                if (criteria.value() instanceof String) {
                    return builder.like(builder.lower(interestsJoin.get("name")), "%" + ((String) criteria.value()).toLowerCase() + "%");
                } else {
                    return builder.equal(interestsJoin.get("name"), criteria.value());
                }
            }


            if (criteria.operation().equals(":")) {
                if (root.get(criteria.key()).getJavaType() == String.class) {
                    return builder.like(root.get(criteria.key()), "%" + criteria.value() + "%");
                } else {
                    return builder.equal(root.get(criteria.key()), criteria.value());
                }
            }

            return null;
        };
    }
}