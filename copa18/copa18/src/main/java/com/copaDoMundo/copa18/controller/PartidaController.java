package com.copaDoMundo.copa18.controller;

import java.time.format.DateTimeFormatter;
import java.util.List;

import com.copaDoMundo.copa18.modelos.Partida;
import com.copaDoMundo.copa18.repository.PartidaRepository;
import com.copaDoMundo.copa18.service.PartidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/partidas")
public class PartidaController {

    @Autowired
    private PartidaService partidaService;
    @Autowired
    private PartidaRepository partidaRepository;


    @GetMapping
    public List<Partida> obterPartidasFormatadas() {
        if (partidaRepository.count() == 0) {
            partidaService.salvarPartidasBancoDados();
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        return partidaRepository.findAll().stream()
                .map(p -> new Partida(
                        p.getId(),
                        p.getData(),
                        p.getTime1(),
                        p.getTime2(),
                        p.getGolsTime1(),
                        p.getGolsTime2()
                ))
                .toList();
    }
    @DeleteMapping
    public ResponseEntity<String> deletarTodasPartidas() {
        partidaRepository.deleteAll();
        return ResponseEntity.ok("Todas as partidas foram deletadas com sucesso.");
    }


    @GetMapping(value = ("/formatadas"), produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> listarPartidasFormatadas() {
        List<Partida> partidas = partidaService.listarPartidas();

        StringBuilder sb = new StringBuilder();
        for (Partida partida : partidas) {
            sb.append(partida.toString()).append("\n");
        }

        return ResponseEntity.ok(sb.toString());
    }
    @GetMapping("/time1/{time1}")
    public List<Partida> buscarPorTime1(@PathVariable String time1){
        return partidaRepository.findByTime1IgnoreCase(time1);
    }
    @GetMapping("/time2/{time2}")
    public List<Partida>buscarPorTime2(@PathVariable String time2){
        return partidaRepository.findByTime2IgnoreCase(time2);
    }
    @GetMapping("/gols1/{golsTime1}")
    public List<Partida>buscarPorGolsTime1(@PathVariable Integer golsTime1){
        return partidaRepository.findByGolsTime1(golsTime1);
    }
    @GetMapping("/gols2/{golsTime2}")
    public List<Partida>buscarPorTime2(@PathVariable Integer golsTime2){
        return partidaRepository.findByGolsTime2(golsTime2);
    }
    @GetMapping("/{time1}/{time2}")
    public ResponseEntity<String>resumoDaPartida(@PathVariable String time1, @PathVariable String time2){
        return partidaRepository
                .findByTime1IgnoreCaseAndTime2IgnoreCase(time1, time2)
                .stream().map(partida ->ResponseEntity.ok(partida.getResumo()))
                .findAny().orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("selecoes/todosOsTimes")
    public List<String> listaDeSelecoes(){
        return partidaService.getTodosTimes();
    }



}



