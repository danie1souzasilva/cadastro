package com.mundoFutebol.futeWorld.controller;

import com.mundoFutebol.futeWorld.modelo.Times;
import com.mundoFutebol.futeWorld.repository.JogadoresRepository;
import com.mundoFutebol.futeWorld.service.TimesService;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public class TimesController {
    private TimesService timesService;
    private JogadoresRepository jogadoresRepository;

    @GetMapping("/times/nomeTimes")
    public List<Times>listarTimes(){
        return timesService.listarTimes();
    }
}
