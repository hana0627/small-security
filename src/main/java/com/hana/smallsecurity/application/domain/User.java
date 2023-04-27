package com.hana.smallsecurity.application.domain;

import com.hana.smallsecurity.application.domain.constant.EncryptionAlgorithm;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    private EncryptionAlgorithm algorithm;


    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Authority> authorities;
}
