package com.ba.mapper;

import com.ba.dto.PaymentTypeDTO;
import com.ba.entity.PaymentType;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-12-31T11:11:04+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.9 (Oracle Corporation)"
)
@Component
public class PaymentTypeMapperImpl implements PaymentTypeMapper {

    @Override
    public PaymentType toEntity(PaymentTypeDTO paymentTypeDTO) {
        if ( paymentTypeDTO == null ) {
            return null;
        }

        PaymentType paymentType = new PaymentType();

        paymentType.setId( paymentTypeDTO.getId() );
        paymentType.setType( paymentTypeDTO.getType() );

        return paymentType;
    }

    @Override
    public PaymentTypeDTO toDTO(PaymentType paymentType) {
        if ( paymentType == null ) {
            return null;
        }

        PaymentTypeDTO paymentTypeDTO = new PaymentTypeDTO();

        paymentTypeDTO.setId( paymentType.getId() );
        paymentTypeDTO.setType( paymentType.getType() );

        return paymentTypeDTO;
    }

    @Override
    public List<PaymentType> toEntityList(List<PaymentTypeDTO> paymentTypeDTOList) {
        if ( paymentTypeDTOList == null ) {
            return null;
        }

        List<PaymentType> list = new ArrayList<PaymentType>( paymentTypeDTOList.size() );
        for ( PaymentTypeDTO paymentTypeDTO : paymentTypeDTOList ) {
            list.add( toEntity( paymentTypeDTO ) );
        }

        return list;
    }

    @Override
    public List<PaymentTypeDTO> toDTOList(List<PaymentType> paymentTypes) {
        if ( paymentTypes == null ) {
            return null;
        }

        List<PaymentTypeDTO> list = new ArrayList<PaymentTypeDTO>( paymentTypes.size() );
        for ( PaymentType paymentType : paymentTypes ) {
            list.add( toDTO( paymentType ) );
        }

        return list;
    }
}
