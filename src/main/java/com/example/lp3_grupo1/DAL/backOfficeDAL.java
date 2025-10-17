package com.example.lp3_grupo1.DAL;

import com.example.lp3_grupo1.BaseDados.LigacaoSQL;
import com.example.lp3_grupo1.Model.Estacao;
import com.example.lp3_grupo1.Model.Linha;
import com.example.lp3_grupo1.Model.Viagem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.List;

/**
 * Classe responsável pelo acesso a dados do BackOffice.
 * Esta classe contém todas as operações de inserção de Linhas, Estações e Viagens na base de dados.
 *
 * <p>As operações são realizadas dentro de uma transação, garantindo integridade:
 * <ul>
 *     <li>Commit é efetuado apenas se todas as inserções forem bem-sucedidas.</li>
 *     <li>Rollback implícito ocorre em caso de exceção.</li>
 * </ul>
 * </p>
 */
public class backOfficeDAL {

    /**
     * Insere registos de Linhas, Estações e Viagens na base de dados.
     * <p>
     * O método realiza as seguintes operações:
     * <ol>
     *     <li>Insere todas as Linhas utilizando batch insert.</li>
     *     <li>Insere todas as Estações individualmente e atualiza os IDs gerados no objeto Estacao.</li>
     *     <li>Insere associações Estacao-Linha na tabela EstacaoLinha usando batch insert.</li>
     *     <li>Insere todas as Viagens individualmente, garantindo integridade referencial com IDs gerados.</li>
     * </ol>
     * <p>
     * Todas as operações são executadas numa única transação.
     * </p>
     *
     * @param linhas   Lista de objetos Linha a inserir na tabela Linha.
     * @param estacoes Lista de objetos Estacao a inserir na tabela Estacao.
     *                 Após inserção, os IDs gerados são atualizados nos próprios objetos.
     * @param viagens  Lista de objetos Viagem a inserir na tabela Viagem.
     *                 Cada viagem referencia Estacao e Linha já inseridos.
     * @throws Exception Lança exceção se ocorrer qualquer erro durante a operação com a base de dados.
     */
    public void insertData(Collection<Linha> linhas, Collection<Estacao> estacoes, List<Viagem> viagens) throws Exception {
        try (Connection con = LigacaoSQL.getInstancia().getConexao()) {
            con.setAutoCommit(false);

            // Inserir Linhas
            String linhaSql = "INSERT INTO Linha (LinhaChave, Nome) VALUES (?, ?)";
            try (PreparedStatement ps = con.prepareStatement(linhaSql, PreparedStatement.RETURN_GENERATED_KEYS)) {
                for (Linha linha : linhas) {
                    ps.setString(1, linha.getLinhaChave());
                    ps.setString(2, linha.getNome());
                    ps.addBatch();
                }
                ps.executeBatch();
            }

            // Inserir Estações
            String estacaoSql = "INSERT INTO Estacao (Nome) VALUES (?)";
            try (PreparedStatement ps = con.prepareStatement(estacaoSql, PreparedStatement.RETURN_GENERATED_KEYS)) {
                for (Estacao estacao : estacoes) {
                    ps.setString(1, estacao.getNome());
                    ps.executeUpdate();
                    try (ResultSet rs = ps.getGeneratedKeys()) {
                        if (rs.next()) {
                            estacao.setIdEstacao(rs.getInt(1));
                        }
                    }
                }
            }

            // Inserir EstacaoLinha
            String estacaoLinhaSql = "INSERT INTO EstacaoLinha (IdEstacao, LinhaChave) VALUES (?, ?)";
            try (PreparedStatement ps = con.prepareStatement(estacaoLinhaSql)) {
                for (Estacao estacao : estacoes) {
                    for (Linha linha : estacao.getLinhas()) {
                        ps.setInt(1, estacao.getEstacao());
                        ps.setString(2, linha.getLinhaChave());
                        ps.addBatch();
                    }
                }
                ps.executeBatch();
            }

            // Inserir Viagens
            String viagemSql = "INSERT INTO Viagem (IdEstacaoPartida, IdEstacaoChegada, LinhaChave) VALUES (?, ?, ?)";
            try (PreparedStatement ps = con.prepareStatement(viagemSql)) {
                for (Viagem viagem : viagens) {
                    ps.setInt(1, viagem.getEstacaoPartida().getEstacao());
                    ps.setInt(2, viagem.getEstacaoChegada().getEstacao());
                    ps.setString(3, viagem.getLinha().getLinhaChave());
                    ps.executeUpdate();
                }
            }

            con.commit();
        }
    }
}
