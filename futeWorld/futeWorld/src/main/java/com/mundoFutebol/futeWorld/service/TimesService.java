package com.mundoFutebol.futeWorld.service;


import com.mundoFutebol.futeWorld.dto.TimesDTO;
import com.mundoFutebol.futeWorld.modelo.Times;
import com.mundoFutebol.futeWorld.repository.TimeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class TimesService {
    @Autowired
    private TimeRepository timeRepository;

    public TimesService(TimeRepository timeRepository) {
        this.timeRepository = timeRepository;
    }
    public Times salvarTimes(TimesDTO timesDTO){
        List<Times> times = new ArrayList<>();
        Times novoTime = new Times();
        novoTime.setNome(timesDTO.nome());
        novoTime.setPais(timesDTO.pais());
        novoTime.setId(timesDTO.id());
        times.add(novoTime);
        return  timeRepository.save(novoTime);

    }

    public List<Times> listarTimes() {
        return timeRepository.findAll();
    }
}
