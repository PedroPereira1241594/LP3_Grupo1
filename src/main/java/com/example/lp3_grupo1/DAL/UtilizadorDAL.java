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

public class UtilizadorDAL {
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

    public List<Utilizador> getAll() {
        List<Utilizador> lista = new ArrayList<>();
        String sql = "SELECT * FROM utilizador";
        
        try {
            Connection con = LigacaoSQL.getInstancia().getConexao();
            if (con == null) {
                System.err.println("Aviso: Conexão à BD não disponível. Usando dados de teste.");
                // Adicionar alguns dados de teste para demonstração
                lista.add(new Utilizador(1, "João Silva", "123456", "joao@email.com", 
                    java.time.LocalDate.of(1990, 5, 15), 1, 1));
                lista.add(new Utilizador(2, "Maria Santos", "abcdef", "maria@email.com", 
                    java.time.LocalDate.of(1985, 8, 22), 0, 1));
                lista.add(new Utilizador(3, "Pedro Costa", "qwerty", "pedro@email.com", 
                    java.time.LocalDate.of(1992, 12, 3), 1, 0));
                return lista;
            }
            
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
