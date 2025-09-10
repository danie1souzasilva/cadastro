package com.copaDoMundo.copa18.repository;

import com.copaDoMundo.copa18.modelos.Partida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartidaRepository extends JpaRepository<Partida, Long> {
    List<Partida> findByTime1IgnoreCase(String time1);
    List<Partida> findByTime2IgnoreCase(String time2);
    List<Partida> findByGolsTime1(Integer golsTime1);
    List<Partida> findByGolsTime2(Integer golsTime2);
    List<Partida> findByTime1IgnoreCaseAndTime2IgnoreCase(String time1, String time2);

}

