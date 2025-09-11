package com.mundoFutebol.futeWorld.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record JogadoresDTO(
        @JsonProperty Long id,
        @JsonProperty String nome,
        @JsonProperty String posicao,
        @JsonProperty Integer idade,
        @JsonProperty Long timeId
) {
}
