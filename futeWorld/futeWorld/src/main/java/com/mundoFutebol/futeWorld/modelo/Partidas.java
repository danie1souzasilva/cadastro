package com.mundoFutebol.futeWorld.modelo;

import jakarta.persistence.*;

import java.sql.Time;
import java.time.LocalDate;

@Entity(name = "Partidas")
public class Partidas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    private LocalDate data;
    private Integer golsTime1;
    private Integer golsTime2;

    @ManyToOne
    private Times time1;

    @ManyToOne
    private Times time2;

    public void setId(Long id) {
        this.id = id;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public void setGolsTime1(Integer golsTime1) {
        this.golsTime1 = golsTime1;
    }

    public void setGolsTime2(Integer golsTime2) {
        this.golsTime2 = golsTime2;
    }

    public void setTime1(Times time1) {
        this.time1 = time1;
    }

    public void setTime2(Times time2) {
        this.time2 = time2;
    }
}
