package com.copaDoMundo.copa18.repository;

import com.copaDoMundo.copa18.modelos.Partida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartidaRepository extends JpaRepository<Partida, Long> {}
