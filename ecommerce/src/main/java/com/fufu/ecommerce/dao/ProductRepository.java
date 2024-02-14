package com.fufu.ecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fufu.ecommerce.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    
}
