package com.copaDoMundo.copa18.controller;

import java.time.format.DateTimeFormatter;
import java.util.List;

import com.copaDoMundo.copa18.modelos.PartidaDTO;
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

    @GetMapping("/copa")
    public List<PartidaDTO> obterPartidasFormatadas() {
        if (partidaRepository.count() == 0) {
            partidaService.salvarPartidasBancoDados();
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        return partidaRepository.findAll().stream()
                .map(p -> new PartidaDTO(
                        p.getId(),
                        p.getData().format(formatter),
                        p.getTime1(),
                        p.getTime2(),
                        p.getGolsTime1(),
                        p.getGolsTime2()
                ))
                .toList();
    }

    @DeleteMapping("/copa")
    public ResponseEntity<String> deletarTodasPartidas() {
        partidaRepository.deleteAll();
        return ResponseEntity.ok("Todas as partidas foram deletadas com sucesso.");
    }


    @GetMapping(value = "/formatadas", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> listarPartidasFormatadas() {
        List<PartidaDTO> partidas = partidaService.listarPartidas();

        StringBuilder sb = new StringBuilder();
        for (PartidaDTO partida : partidas) {
            sb.append(partida.toString()).append("\n");
        }

        return ResponseEntity.ok(sb.toString());
    }


}



