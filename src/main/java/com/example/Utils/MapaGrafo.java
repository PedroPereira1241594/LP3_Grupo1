package com.example.Utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
import java.util.*;

public class MapaGrafo {
    private Map<String, List<String>> grafo = new HashMap<>();

    public void adicionarLigacao(String a, String b) {
        grafo.computeIfAbsent(a, k -> new ArrayList<>()).add(b);
        grafo.computeIfAbsent(b, k -> new ArrayList<>()).add(a);
    }

    public List<List<String>> encontrarCaminhos(String origem, String destino) {
        List<List<String>> todosCaminhos = new ArrayList<>();
        Set<String> visitados = new HashSet<>();
        List<String> caminhoAtual = new ArrayList<>();

        dfs(origem, destino, visitados, caminhoAtual, todosCaminhos);
        return todosCaminhos;
    }

    public void carregarGrafoDaBaseDeDados(Connection conn) throws SQLException {
        // 1️⃣ Obter todas as linhas e as respetivas estações ordenadas pela sequência
        String sql = """
            SELECT l.LinhaChave, e.Nome AS Estacao
            FROM EstacaoLinha el
            JOIN Estacao e ON el.IdEstacao = e.IdEstacao
            JOIN Linha l ON el.LinhaChave = l.LinhaChave
            ORDER BY l.LinhaChave, e.IdEstacao
        """;

        Map<String, List<String>> estacoesPorLinha = new HashMap<>();

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String linha = rs.getString("LinhaChave");
                String estacao = rs.getString("Estacao");
                estacoesPorLinha.computeIfAbsent(linha, k -> new ArrayList<>()).add(estacao);
            }
        }

        // 2️⃣ Criar ligações entre estações consecutivas dentro de cada linha
        for (List<String> estacoes : estacoesPorLinha.values()) {
            for (int i = 0; i < estacoes.size() - 1; i++) {
                String atual = estacoes.get(i);
                String seguinte = estacoes.get(i + 1);
                adicionarLigacao(atual, seguinte);
            }
        }
    }

    private void dfs(String atual, String destino, Set<String> visitados, List<String> caminhoAtual, List<List<String>> todosCaminhos) {
        visitados.add(atual);
        caminhoAtual.add(atual);

        if (atual.equals(destino)) {
            todosCaminhos.add(new ArrayList<>(caminhoAtual));
        } else {
            for (String vizinho : grafo.getOrDefault(atual, new ArrayList<>())) {
                if (!visitados.contains(vizinho)) {
                    dfs(vizinho, destino, visitados, caminhoAtual, todosCaminhos);
                }
            }
        }

        // Backtracking
        caminhoAtual.remove(caminhoAtual.size() - 1);
        visitados.remove(atual);
    }
}
