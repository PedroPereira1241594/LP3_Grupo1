package com.example.lp3_grupo1.Model;

public class Viagem {
    private int idViagem;
    private Estacao partida;
    private Estacao chegada;
    private Linha linha;

    public Viagem() {}

    public Viagem(int idViagem, Estacao partida, Estacao chegada, Linha linha) {
        this.idViagem = idViagem;
        this.partida = partida;
        this.chegada = chegada;
        this.linha = linha;
    }

    public int getIdViagem() {
        return idViagem;
    }

    public void setIdViagem(int idViagem) {
        this.idViagem = idViagem;
    }

    public Estacao getEstacaoPartida() {
        return partida;
    }

    public void setEstacaoPartida(Estacao partida) {
        this.partida = partida;
    }

    public Estacao getEstacaoChegada() {
        return chegada;
    }

    public void setEstacaoChegada(Estacao chegada) {
        this.chegada = chegada;
    }

    public Linha getLinha() {
        return linha;
    }

    public void setLinha(Linha linha) {
        this.linha = linha;
    }
}
