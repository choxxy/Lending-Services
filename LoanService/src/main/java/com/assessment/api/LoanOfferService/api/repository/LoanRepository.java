package com.assessment.api.LoanOfferService.api.repository;

import com.assessment.CommonService.api.enums.LoanStatus;
import com.assessment.api.LoanOfferService.api.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.ws.rs.QueryParam;
import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

     Loan findLoanByUserIdAndStatus(long userId, LoanStatus status);

     List<Loan> findLoanByStatus(LoanStatus status);

    // @Query("FROM Loan where userId=:userId and status=:status")
    // Loan existsByUserId(@Param("userId") long userId, @Param("status") LoanStatus status);
}