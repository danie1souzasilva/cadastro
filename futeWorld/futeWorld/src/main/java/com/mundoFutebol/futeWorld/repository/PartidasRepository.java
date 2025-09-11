package com.mundoFutebol.futeWorld.repository;

import com.mundoFutebol.futeWorld.modelo.Partidas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartidasRepository extends JpaRepository<Partidas, Long> {
}
