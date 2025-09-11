package com.mundoFutebol.futeWorld.modelo;

import jakarta.persistence.*;

import java.util.List;

@Entity(name = "Times")
public class Times {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String nome;
    public String pais;

    public Times() {
    }

    public Times(Long id, String nome, String pais) {
        this.id = id;
        this.nome = nome;
        this.pais = pais;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getPais() {
        return pais;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public void setJogadores(List<Jogadores> jogadores) {
        this.jogadores = jogadores;
    }

    @OneToMany(mappedBy = "times")
    private List<Jogadores> jogadores;


}
