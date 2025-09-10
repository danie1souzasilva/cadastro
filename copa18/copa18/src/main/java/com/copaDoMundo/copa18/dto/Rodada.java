package com.copaDoMundo.copa18.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Rodada(@JsonProperty("name" )String nome,
                     @JsonProperty("matches") List<Partidas> jogos) {

}
