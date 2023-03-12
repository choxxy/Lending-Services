package com.assessment.api.LoanOfferService.api.repository;

import com.assessment.api.LoanOfferService.api.entity.LoanRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends JpaRepository<LoanRequest, Long> {
}