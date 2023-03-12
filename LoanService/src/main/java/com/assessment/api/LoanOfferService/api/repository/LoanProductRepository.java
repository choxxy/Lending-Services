package com.assessment.api.LoanOfferService.api.repository;

import com.assessment.api.LoanOfferService.api.entity.LoanProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanProductRepository extends JpaRepository<LoanProduct, Long> {

    List<LoanProduct> findByMaxAllowableLimitLessThanEqual(long amount);
}