package com.mundoFutebol.futeWorld.modelo;

import jakarta.persistence.*;

@Entity(name = "Jogadores")
public class Jogadores {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String nome;
    public Posicao posicao;
    public  Integer idade;

    public Jogadores() {
    }

    public Jogadores(Long id, String nome, Posicao posicao, Integer idade) {
        this.id = id;
        this.nome = nome;
        this.posicao = posicao;
        this.idade = idade;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Posicao getPosicao() {
        return posicao;
    }

    public Integer getIdade() {
        return idade;
    }
    @ManyToOne
    private Times times;
}
