package com.example.lp3_grupo1.Controller;

import com.example.lp3_grupo1.BLL.ListarClientesBLL;
import com.example.lp3_grupo1.BLL.ValidacaoClientesBLL;
import com.example.lp3_grupo1.Model.Utilizador;
import com.example.Utils.Tools.estadoUtilizador;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;

import java.util.List;

public class ListagemClienteController {

    @FXML private TableView<Utilizador> tabelaPessoas;
    @FXML private TableColumn<Utilizador, Integer> colId;
    @FXML private TableColumn<Utilizador, String> colNome;
    @FXML private TableColumn<Utilizador, String> colPassword;
    @FXML private TableColumn<Utilizador, String> colEmail;
    @FXML private TableColumn<Utilizador, java.time.LocalDate> colDataNascimento;
    @FXML private TableColumn<Utilizador, estadoUtilizador> colEstado;
    @FXML private TableColumn<Utilizador, Integer> colTipo;

    @FXML
    private void initialize() {
        // Config das colunas “normais” (ajusta aos teus getters)
        colId.setCellValueFactory(c -> new ReadOnlyObjectWrapper<>(c.getValue().getId()));
        colNome.setCellValueFactory(c -> new ReadOnlyObjectWrapper<>(c.getValue().getNome()));
        colPassword.setCellValueFactory(c -> new ReadOnlyObjectWrapper<>(c.getValue().getPassword()));
        colEmail.setCellValueFactory(c -> new ReadOnlyObjectWrapper<>(c.getValue().getEmail()));
        colDataNascimento.setCellValueFactory(c -> new ReadOnlyObjectWrapper<>(c.getValue().getDataNascimento()));
        colTipo.setCellValueFactory(c -> new ReadOnlyObjectWrapper<>(c.getValue().getTipo()));

        // Tabela editável
        tabelaPessoas.setEditable(true);

        // Coluna Estado com dropdown (enum na célula, int no modelo)
        colEstado.setCellValueFactory (cd -> {
            Utilizador u = cd.getValue();
            estadoUtilizador e = estadoUtilizador.fromCodigo(u.getEstado()); // getEstado() = int
            return new ReadOnlyObjectWrapper<>(e);
        });
        colEstado.setCellFactory(ComboBoxTableCell.forTableColumn(estadoUtilizador.values()));
        colEstado.setOnEditCommit(evt -> {
            Utilizador u = evt.getRowValue();
            estadoUtilizador novo = evt.getNewValue();
            if (novo != null) {
                u.setEstado(novo.getCodigo()); // guarda como int no modelo
                tabelaPessoas.refresh();
            }
        });

        // Carregar dados através da BLL
        ListarClientesBLL listarClientesBLL = new ListarClientesBLL();
        List<Utilizador> dados = listarClientesBLL.listar();
        tabelaPessoas.setItems(FXCollections.observableArrayList(dados));
    }

    @FXML
    private void onGuardarAlteracoes() {
        // SIMPLES: atualiza todos os itens da tabela
        ListarClientesBLL bll = new ListarClientesBLL();
        boolean sucessoTotal = true;

        for (Utilizador u : tabelaPessoas.getItems()) {
            // grava sempre o estado atual do modelo
            sucessoTotal &= bll.atualizarEstado(u.getId(), u.getEstado()); // int código
        }

        if (sucessoTotal) {
            mostrarInfo("Sucesso", "Estados atualizados com sucesso!");
        } else {
            mostrarErro("Erro", "Alguns estados não foram atualizados.");
        }
    }

    private void mostrarInfo(String titulo, String msg) {
        Alert a = new Alert(Alert.AlertType.INFORMATION, msg, ButtonType.OK);
        a.setTitle(titulo);
        a.setHeaderText(null);
        a.showAndWait();
    }

    private void mostrarErro(String titulo, String msg) {
        Alert a = new Alert(Alert.AlertType.ERROR, msg, ButtonType.OK);
        a.setTitle(titulo);
        a.setHeaderText(null);
        a.showAndWait();
    }
}
