package com.ba.mapper;

import com.ba.dto.PaymentTypeDTO;
import com.ba.entity.PaymentType;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PaymentTypeMapper {
    PaymentType toEntity(PaymentTypeDTO paymentTypeDTO);
    PaymentTypeDTO toDTO(PaymentType paymentType);
    List<PaymentType> toEntityList(List<PaymentTypeDTO> paymentTypeDTOList);
    List<PaymentTypeDTO> toDTOList(List<PaymentType> paymentTypes);
}
