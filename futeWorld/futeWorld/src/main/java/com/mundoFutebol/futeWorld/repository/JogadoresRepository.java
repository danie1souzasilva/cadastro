package com.mundoFutebol.futeWorld.repository;

import com.mundoFutebol.futeWorld.modelo.Jogadores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JogadoresRepository extends JpaRepository<Jogadores, Long> {
}
