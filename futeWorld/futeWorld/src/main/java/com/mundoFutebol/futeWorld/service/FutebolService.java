package com.mundoFutebol.futeWorld.service;

import com.mundoFutebol.futeWorld.repository.JogadoresRepository;
import com.mundoFutebol.futeWorld.repository.PartidasRepository;
import com.mundoFutebol.futeWorld.repository.TimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FutebolService {
    @Autowired
    private TimeRepository timeRepository;
    @Autowired
    private JogadoresRepository jogadoresRepository;
    @Autowired
    private PartidasRepository partidasRepository;

    public FutebolService(TimeRepository timeRepository, JogadoresRepository jogadoresRepository, PartidasRepository partidasRepository) {
        this.timeRepository = timeRepository;
        this.jogadoresRepository = jogadoresRepository;
        this.partidasRepository = partidasRepository;
    }
}
