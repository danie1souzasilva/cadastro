package com.mundoFutebol.futeWorld.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public record PartidasDTO (
        @JsonProperty Long id,
        @JsonProperty LocalDate data,
        @JsonProperty Long time1Id,
        @JsonProperty Long time2Id,
        @JsonProperty Integer golsTime1,
        @JsonProperty Integer golsTime2
){
}
