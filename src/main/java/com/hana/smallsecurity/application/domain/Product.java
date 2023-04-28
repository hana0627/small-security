package com.hana.smallsecurity.application.domain;

import com.hana.smallsecurity.application.domain.constant.Currency;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double price;
    @Enumerated(EnumType.STRING)
    private Currency currency;
}
