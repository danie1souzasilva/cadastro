package com.mundoFutebol.futeWorld.service;

import com.mundoFutebol.futeWorld.dto.PartidasDTO;
import com.mundoFutebol.futeWorld.modelo.Partidas;
import com.mundoFutebol.futeWorld.modelo.Times;
import com.mundoFutebol.futeWorld.repository.PartidasRepository;
import com.mundoFutebol.futeWorld.repository.TimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PartidasService {
    @Autowired
    private PartidasRepository partidasRepository;

    @Autowired
    private TimeRepository timeRepository;


    public Partidas salvarPartidas(PartidasDTO partidasDTO) {
        Times time1 = timeRepository.findById(partidasDTO.time1Id())
                .orElseThrow(() -> new RuntimeException("Time A não encontrado"));

        Times time2 = timeRepository.findById(partidasDTO.time2Id())
                .orElseThrow(() -> new RuntimeException("Time B não encontrado"));

        Partidas partida = new Partidas();
        partida.setData(partidasDTO.data());
        partida.setTime1(time1);
        partida.setTime2(time2);
        partida.setGolsTime1(partidasDTO.golsTime1());
        partida.setGolsTime2(partidasDTO.golsTime2());

        return partidasRepository.save(partida);
    }


    public PartidasService(PartidasRepository partidasRepository) {
        this.partidasRepository = partidasRepository;
    }

    public List<Partidas> listarJogos() {
        return partidasRepository.findAll();
    }
}
