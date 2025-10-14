package com.example.lp3_grupo1.BaseDados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class LigacaoSQL {
    private static LigacaoSQL instanciaUnica;
    private Connection conexao;

    private static final String URL = "jdbc:sqlserver://ctespbd.dei.isep.ipp.pt:1433;databaseName=2025_lp3_feira_g1;encrypt=optional;trustServerCertificate=true;";
    private static final String USER = "2025_lp3_feira_g1";
    private static final String PASSWORD = "Grupo1LP3";

    private LigacaoSQL() {
        conectar();
    }

    private void conectar() {
        try {
            conexao = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Ligado à base de dados!");
        } catch (SQLException e) {
            System.err.println("Erro ao ligar à base de dados: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static synchronized LigacaoSQL getInstancia() {
        if (instanciaUnica == null) {
            instanciaUnica = new LigacaoSQL();
        }
        return instanciaUnica;
    }

    public Connection getConexao() {
        try {
            if (conexao == null || conexao.isClosed()) {
                conectar();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            conectar();
        }
        return conexao;
    }
}
