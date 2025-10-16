package com.example.lp3_grupo1.Model;

import java.time.LocalDate;

/**
 * Modelo de domínio que representa um utilizador do sistema.
 */
public class Utilizador {
    protected int id;
    protected String nome;
    protected String password;
    protected String email;
    protected LocalDate dataNascimento;
    protected int Tipo;

    /**
     * Cria um novo utilizador.
     *
     * @param id identificador único.
     * @param nome nome do utilizador.
     * @param password password do utilizador.
     * @param email email do utilizador.
     * @param dataNascimento data de nascimento.
     * @param tipo tipo/perfil do utilizador.
     * @param estado estado do utilizador (ex.: 1 pendente, 2 aprovado).
     */
    public Utilizador(int id, String nome, String password, String email, LocalDate dataNascimento, int tipo, int estado) {
        this.id = id;
        this.nome = nome;
        this.password = password;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.Tipo = tipo;
        this.estado = estado;
    }

    /**
     * @return identificador do utilizador.
     */
    public int getId() {
        return id;
    }

    /**
     * @param id novo identificador do utilizador.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return nome do utilizador.
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome novo nome do utilizador.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return password do utilizador.
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password nova password do utilizador.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return email do utilizador.
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email novo email do utilizador.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return data de nascimento do utilizador.
     */
    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    /**
     * @param dataNascimento nova data de nascimento.
     */
    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    /**
     * @return tipo/perfil do utilizador.
     */
    public int getTipo() {
        return Tipo;
    }

    /**
     * @param tipo novo tipo/perfil do utilizador.
     */
    public void setTipo(int tipo) {
        Tipo = tipo;
    }

    /**
     * @return estado do utilizador.
     */
    public int getEstado() {
        return estado;
    }

    /**
     * @param estado novo estado do utilizador.
     */
    public void setEstado(int estado) {
        this.estado = estado;
    }

    protected int estado;
}

