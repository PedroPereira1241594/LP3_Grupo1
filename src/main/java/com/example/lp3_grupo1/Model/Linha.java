package com.example.lp3_grupo1.Model;

import java.util.HashSet;
import java.util.Set;

public class Linha {
    private String linhaChave;
    private String nome;
    private Set<Estacao> estacoes = new HashSet<>();

    public Linha() {}

    public Linha(String linhaChave, String nome) {
        this.linhaChave = linhaChave;
        this.nome = nome;
    }

    public String getLinhaChave() {
        return linhaChave;
    }

    public void setLinhaChave(String linhaChave) {
        this.linhaChave = linhaChave;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Set<Estacao> getEstacoes() {
        return estacoes;
    }

    public void addEstacao(Estacao estacao) {
        this.estacoes.add(estacao);
    }
}

