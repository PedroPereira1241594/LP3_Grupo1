package com.example.lp3_grupo1.Controller;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import com.example.lp3_grupo1.Model.Utilizador;
import com.example.lp3_grupo1.BLL.ValidacaoClientesBLL;

public class ValidacaoClientesController {
    @FXML
    private void closeApplicationButtonOnAction(ActionEvent event) {
        // ação do botão Home
    }

    @FXML
    private void loginButtonOnAction(ActionEvent event){
        //ação do botão Home
    }

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

    @FXML
    private void initialize() {
        // Mapear colunas para propriedades do modelo Utilizador
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colDataNascimento.setCellValueFactory(new PropertyValueFactory<>("dataNascimento"));
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));

        // Carregar dados através da BLL
        ValidacaoClientesBLL validacaoClientesBLL = new ValidacaoClientesBLL();
        List<Utilizador> dados = validacaoClientesBLL.listar();
        tabelaPessoas.setItems(FXCollections.observableArrayList(dados));
    }
}
