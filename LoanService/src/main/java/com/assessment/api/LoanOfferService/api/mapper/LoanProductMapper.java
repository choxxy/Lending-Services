package com.assessment.api.LoanOfferService.api.mapper;

import com.assessment.CommonService.api.dto.LoanProductDto;
import com.assessment.CommonService.api.mapper.EntityMapper;
import com.assessment.api.LoanOfferService.api.entity.LoanProduct;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LoanProductMapper extends EntityMapper<LoanProductDto, LoanProduct> {
}