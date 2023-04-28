package com.hana.smallsecurity.application.service;

import com.hana.smallsecurity.application.domain.Product;
import com.hana.smallsecurity.application.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;
    
    //TODO : 원래 Dto로 Wrapping 해서 반환해야하는거 알지만,
    // 이정도는 편의를 위해 넘어가자
    public List<Product> findAll() {
        return productRepository.findAll();
    }
}
