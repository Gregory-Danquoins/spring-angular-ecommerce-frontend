package com.fufu.ecommerce.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.fufu.ecommerce.entity.State;

@CrossOrigin(origins={"http://localhost:4200","http://localhost:55175"})
public interface StateRepository extends JpaRepository<State, Integer> {
    
    List<State> findByCountryCode(@Param("code") String code);
}
