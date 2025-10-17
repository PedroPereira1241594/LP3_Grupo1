package com.example.lp3_grupo1.Model;

import java.util.HashSet;
import java.util.Set;

public class Estacao {
    private int idEstacao;
    private String nome;
    private Set<Linha> linhas = new HashSet<>();

    public Estacao() {}

    public Estacao(int idEstacao, String nome) {
        this.idEstacao = idEstacao;
        this.nome = nome;
    }

    public int getEstacao() {
        return idEstacao;
    }

    public void setIdEstacao(int idEstacao) {
        this.idEstacao = idEstacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Set<Linha> getLinhas() {
        return linhas;
    }

    public void addLinha(Linha linha) {
        this.linhas.add(linha);
    }
}
