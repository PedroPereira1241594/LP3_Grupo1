package com.example.lp3_grupo1;

import com.example.Utils.MapaGrafo;
import com.example.lp3_grupo1.BaseDados.LigacaoSQL;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MapaGrafoTest {
    @Test
    void adicionarLigacao_criaCaminhoDireto() throws SQLException {
        MapaGrafo mapa = new MapaGrafo();
        Connection conn = LigacaoSQL.getInstancia().getConexao();
        mapa.carregarGrafoDaBaseDeDados(conn);

        List<List<String>> caminhos = mapa.encontrarCaminhos("Guarda Rios", "Calhandriz");

        System.out.println(caminhos);

        assertFalse(caminhos.isEmpty(), "Deve existir pelo menos um caminho entre Guarda Rios e Calhandriz");
        assertTrue(
                caminhos.stream().anyMatch(c -> c.size() == 2 && "Guarda Rios".equals(c.get(0)) && "Calhandriz".equals(c.get(1))),
                "Deve existir um caminho direto [Guarda Rios, Calhandriz]"
        );
    }
}
