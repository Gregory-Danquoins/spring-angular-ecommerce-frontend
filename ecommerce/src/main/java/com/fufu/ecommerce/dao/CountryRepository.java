package com.fufu.ecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.fufu.ecommerce.entity.Country;

@CrossOrigin(origins={"http://localhost:4200","http://localhost:55175"})
public interface CountryRepository extends JpaRepository<Country, Integer> {
    
}
