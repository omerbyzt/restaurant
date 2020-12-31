package com.ba.mapper;

import com.ba.dto.CreditCardDTO;
import com.ba.entity.CreditCard;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CreditCardMapper {

    CreditCard toEntity(CreditCardDTO dto);
    CreditCardDTO toDTO(CreditCard creditCard);
    List<CreditCard> toEntityList(List<CreditCardDTO> creditCardDTOList);
    List<CreditCardDTO> toDTOList(List<CreditCard> creditCards);
}