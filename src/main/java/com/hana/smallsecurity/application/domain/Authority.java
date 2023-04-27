package com.hana.smallsecurity.application.domain;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "user")
    private User user;
}
