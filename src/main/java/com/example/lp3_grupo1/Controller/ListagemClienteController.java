package com.example.lp3_grupo1.Controller;

import com.example.lp3_grupo1.BLL.ListarClientesBLL;
import com.example.lp3_grupo1.Model.Utilizador;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;


public class ListagemClienteController {

    @FXML
    private TableView<Utilizador> tabelaPessoas;

    @FXML
    private TableColumn<Utilizador, Integer> colId;

    @FXML
    private TableColumn<Utilizador, String> colNome;

    @FXML
    private TableColumn<Utilizador, String> colPassword;

    @FXML
    private TableColumn<Utilizador, String> colEmail;

    @FXML
    private TableColumn<Utilizador, java.time.LocalDate> colDataNascimento;

    @FXML
    private TableColumn<Utilizador, Integer> colEstado;

    @FXML
    private TableColumn<Utilizador, Integer> colTipo;

    /**
     * Inicializa a tabela, configura o mapeamento de colunas, carrega os dados
     * e define o estado do botão "Aceitar todos" conforme existência de itens.
     */
    @FXML
    private void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colDataNascimento.setCellValueFactory(new PropertyValueFactory<>("dataNascimento"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));

        // Carregar dados da BLL
        ListarClientesBLL listarClientesBLL = new ListarClientesBLL();
        List<Utilizador> dados = listarClientesBLL.listar();

        // 3️⃣ Coloca os dados na TableView
        tabelaPessoas.setItems(FXCollections.observableArrayList(dados));
    }

    }
