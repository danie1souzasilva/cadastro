package com.copaDoMundo.copa18.modelos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Copa (@JsonProperty("name") String nome,
                    @JsonProperty("rounds") List<Rodada> rodadas){}



