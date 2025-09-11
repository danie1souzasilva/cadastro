package com.mundoFutebol.futeWorld.service;

import com.mundoFutebol.futeWorld.repository.JogadoresRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class JogadoresService {
    @Autowired
    private JogadoresRepository jogadoresRepository;

    public JogadoresService(JogadoresRepository jogadoresRepository) {
        this.jogadoresRepository = jogadoresRepository;
    }
}
