package com.ba.repository;

import com.ba.entity.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCardRepository extends JpaRepository<CreditCard,Long> {

    CreditCard findCreditCardByNumber(String number);
}
