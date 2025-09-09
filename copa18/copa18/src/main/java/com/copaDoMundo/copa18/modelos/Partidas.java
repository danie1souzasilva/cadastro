package com.copaDoMundo.copa18.modelos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Partidas(
                       @JsonProperty("date")String data,
                       @JsonProperty("team1")Time time1,
                       @JsonProperty("team2")Time time2,
                       @JsonProperty("score1")int golsTime1,
                       @JsonProperty("score2")int golsTime2) {

}
