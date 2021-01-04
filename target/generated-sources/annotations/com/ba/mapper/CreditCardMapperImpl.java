package com.ba.mapper;

import com.ba.dto.CreditCardDTO;
import com.ba.entity.CreditCard;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-01-03T17:09:16+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.9 (Oracle Corporation)"
)
@Component
public class CreditCardMapperImpl implements CreditCardMapper {

    @Override
    public CreditCard toEntity(CreditCardDTO dto) {
        if ( dto == null ) {
            return null;
        }

        CreditCard creditCard = new CreditCard();

        creditCard.setId( dto.getId() );
        creditCard.setName( dto.getName() );
        creditCard.setNumber( dto.getNumber() );
        creditCard.setExpiry( dto.getExpiry() );
        creditCard.setCvc( dto.getCvc() );

        return creditCard;
    }

    @Override
    public CreditCardDTO toDTO(CreditCard creditCard) {
        if ( creditCard == null ) {
            return null;
        }

        CreditCardDTO creditCardDTO = new CreditCardDTO();

        creditCardDTO.setId( creditCard.getId() );
        creditCardDTO.setName( creditCard.getName() );
        creditCardDTO.setNumber( creditCard.getNumber() );
        creditCardDTO.setExpiry( creditCard.getExpiry() );
        creditCardDTO.setCvc( creditCard.getCvc() );

        return creditCardDTO;
    }

    @Override
    public List<CreditCard> toEntityList(List<CreditCardDTO> creditCardDTOList) {
        if ( creditCardDTOList == null ) {
            return null;
        }

        List<CreditCard> list = new ArrayList<CreditCard>( creditCardDTOList.size() );
        for ( CreditCardDTO creditCardDTO : creditCardDTOList ) {
            list.add( toEntity( creditCardDTO ) );
        }

        return list;
    }

    @Override
    public List<CreditCardDTO> toDTOList(List<CreditCard> creditCards) {
        if ( creditCards == null ) {
            return null;
        }

        List<CreditCardDTO> list = new ArrayList<CreditCardDTO>( creditCards.size() );
        for ( CreditCard creditCard : creditCards ) {
            list.add( toDTO( creditCard ) );
        }

        return list;
    }
}
