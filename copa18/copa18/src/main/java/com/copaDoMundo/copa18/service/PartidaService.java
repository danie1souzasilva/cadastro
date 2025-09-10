package com.copaDoMundo.copa18.service;

import com.copaDoMundo.copa18.dto.Copa;
import com.copaDoMundo.copa18.dto.Partidas;
import com.copaDoMundo.copa18.dto.Rodada;
import com.copaDoMundo.copa18.modelos.*;
import com.copaDoMundo.copa18.repository.PartidaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PartidaService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String URL =
            "https://raw.githubusercontent.com/openfootball/world-cup.json/master/2018/worldcup.json";

    private final PartidaRepository partidaRepository;


    public PartidaService(PartidaRepository partidaRepository) {
        this.partidaRepository = partidaRepository;

    }

    public List<Partida> salvarPartidasBancoDados() {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String json = restTemplate.getForObject(URL, String.class);

            ObjectMapper mapper = new ObjectMapper();
            Copa copa = mapper.readValue(json, Copa.class);

            List<Partida> partidasSalvas = new ArrayList<>();

            for (Rodada rodada : copa.rodadas()) {
                for (Partidas pj : rodada.jogos()) {
                    LocalDate dataFormatada = LocalDate.parse(pj.data());
                    Partida partida = new Partida(
                            null,
                            dataFormatada,
                            pj.time1().nome(),
                            pj.time2().nome(),
                            pj.golsTime1(),
                            pj.golsTime2()
                    );
                    partidasSalvas.add(partidaRepository.save(partida));
                }
            }

            return partidasSalvas;

        } catch (IOException e) {
            throw new RuntimeException("Erro ao processar os dados da Copa do Mundo", e);
        }
    }
    public List<String> getTodosTimes(){
        String sql =
                "SELECT time1 AS time FROM partidas "
                        + "UNION "
                        + "SELECT time2 AS time FROM partidas "
                        + "ORDER BY time";
        return jdbcTemplate.queryForList(sql, String.class);
    }

    public List<Partida> listarPartidas(){
         return partidaRepository.findAll();
    }


}
