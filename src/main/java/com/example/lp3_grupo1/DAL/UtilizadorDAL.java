package com.example.lp3_grupo1.DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.lp3_grupo1.BaseDados.LigacaoSQL;
import com.example.lp3_grupo1.Model.Utilizador;

/**
 * Data Access Layer (DAL) para a entidade {@link Utilizador}.
 * Responsável pelo acesso à base de dados para operações de consulta e atualização.
 */
public class UtilizadorDAL {
    /**
     * Insere um novo utilizador.
     * Atualmente não implementado (código comentado).
     *
     * @param u utilizador a inserir.
     */
    public void insert(Utilizador u) {
        /* String sql = "INSERT INTO Utilizador(Nome, Email, password) VALUES (?, ?, ?)";
        try (Connection con = LigacaoSQL.getInstancia().getConexao();
             PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, u.getNome());
            stmt.setString(2, u.getEmail());
            stmt.setString(3, u.getPassword());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } */
    }

    /**
     * Obtém todos os utilizadores.
     *
     * @return lista completa de {@link Utilizador}.
     */
    public List<Utilizador> getAll() {
        List<Utilizador> lista = new ArrayList<>();
        String sql = "SELECT * FROM utilizador";
        
        try {
            Connection con = LigacaoSQL.getInstancia().getConexao();
            
            try (Statement stmt = con.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {

                while (rs.next()) {
                    Utilizador u = new Utilizador(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getString("password"),
                            rs.getString("email"),
                            rs.getDate("data_nascimento").toLocalDate(),
                            rs.getInt("tipo"),
                            rs.getInt("estado")
                    );
                    lista.add(u);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    /**
     * Aprova um cliente atualizando o seu estado para 2 (aprovado).
     *
     * @param u utilizador a aprovar.
     */
    public void AprovarCliente(Utilizador u) {
        String sql = "UPDATE utilizador SET estado=2 WHERE id=?";
        try (Connection con = LigacaoSQL.getInstancia().getConexao();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, u.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Obtém utilizadores com estado pendente (estado = 1).
     *
     * @return lista de {@link Utilizador} pendentes.
     */
    public List<Utilizador> getPendentes() {
        List<Utilizador> lista = new ArrayList<>();
        String sql = "SELECT * FROM utilizador WHERE estado = 1";
        
        try {
            Connection con = LigacaoSQL.getInstancia().getConexao();
            
            try (Statement stmt = con.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {

                while (rs.next()) {
                    Utilizador u = new Utilizador(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getString("password"),
                            rs.getString("email"),
                            rs.getDate("data_nascimento").toLocalDate(),
                            rs.getInt("tipo"),
                            rs.getInt("estado")
                    );
                    lista.add(u);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
}

