package com.assessment.api.LoanOfferService.api.mapper;

import com.assessment.CommonService.api.dto.LoanDto;
import com.assessment.CommonService.api.mapper.EntityMapper;
import com.assessment.api.LoanOfferService.api.entity.Loan;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LoanMapper extends EntityMapper<LoanDto, Loan> {
}