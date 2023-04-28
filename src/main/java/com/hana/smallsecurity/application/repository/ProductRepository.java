package com.hana.smallsecurity.application.repository;

import com.hana.smallsecurity.application.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
