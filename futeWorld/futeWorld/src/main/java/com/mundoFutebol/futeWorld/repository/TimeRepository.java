package com.mundoFutebol.futeWorld.repository;

import com.mundoFutebol.futeWorld.modelo.Times;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimeRepository extends JpaRepository<Times, Long> {
    List<Times> findByPaisIgnoreCase(String pais);
    List<Times> findByNameIgnoreCase(String name);}


