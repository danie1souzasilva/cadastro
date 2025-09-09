package com.copaDoMundo.copa18.modelos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;


@Entity(name = "Partida")
@Table(name = "partidas")
public class Partida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate data;

    private String time1;
    private String time2;
    private Integer golsTime1;
    private Integer golsTime2;

    public Partida() {}

    public Partida(Long id, LocalDate data, String time1, String time2, Integer golsTime1, Integer golsTime2) {
        this.id = id;
        this.data = data;
        this.time1 = time1;
        this.time2 = time2;
        this.golsTime1 = golsTime1;
        this.golsTime2 = golsTime2;
    }

    public Long getId() {
        return id;
    }


    public LocalDate getData() {
        return data;
    }

    public String getTime1() {
        return time1;
    }

    public String getTime2() {
        return time2;
    }

    public Integer getGolsTime1() {
        return golsTime1;
    }

    public Integer getGolsTime2() {
        return golsTime2;
    }
}

