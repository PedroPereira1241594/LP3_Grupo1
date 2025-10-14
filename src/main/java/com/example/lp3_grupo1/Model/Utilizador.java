package com.example.lp3_grupo1.Model;

import java.time.LocalDate;

public class Utilizador {
    protected int id;
    protected String nome;
    protected String password;
    protected String email;
    protected LocalDate dataNascimento;
    protected int Tipo;

    public Utilizador(int id, String nome, String password, String email, LocalDate dataNascimento, int tipo, int estado) {
        this.id = id;
        this.nome = nome;
        this.password = password;
        this.email = email;
        this.dataNascimento = dataNascimento;
        Tipo = tipo;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public int getTipo() {
        return Tipo;
    }

    public void setTipo(int tipo) {
        Tipo = tipo;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    protected int estado;
}
