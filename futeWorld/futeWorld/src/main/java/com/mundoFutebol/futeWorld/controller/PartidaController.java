package com.mundoFutebol.futeWorld.controller;

import com.mundoFutebol.futeWorld.modelo.Partidas;
import com.mundoFutebol.futeWorld.repository.PartidasRepository;
import com.mundoFutebol.futeWorld.service.PartidasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/partidas")
public class PartidaController {
    @Autowired
    private PartidasService partidasService;
    @Autowired
    private PartidasRepository partidasRepository;

    @GetMapping("/partidas")
    public List<Partidas> listarPartidas(){
        return partidasService.listarJogos();
    }

}
