package com.example.lp3_grupo1.Controller;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import com.example.Utils.Tools;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import java.io.IOException;

/**
 * Controller para a view MenuGestor.fxml
 * 
 * @author Grupo 1
 */
public class MenuGestorController {

    @FXML
    private AnchorPane contentBox; // <-- isto liga ao fx:id="contentBox" do FXML base

    /**
     * Carrega a view de listagem de clientes
     */
    @FXML
    private void onListarClientes() {
        Tools.carregarConteudo(contentBox, "/com/example/lp3_grupo1/view/ListagemCliente.fxml");
    }

    /**
     * Carrega a view de gestão de clientes
     */
    @FXML
    private void onGestaoClientes() {
        Tools.carregarConteudo(contentBox, "/com/example/lp3_grupo1/view/GestaoCliente.fxml");
    }

    /**
     * Carrega a view de validação de clientes
     */
    @FXML
    private void onValidarClientes() {
        Tools.carregarConteudo(contentBox, "/com/example/lp3_grupo1/view/ValidacaoClientes.fxml");
    }

    /**
     * Carrega a view de carregamento do mapa
     */
    @FXML
    private void onInserirMapa() {
        Tools.carregarConteudo(contentBox, "/com/example/lp3_grupo1/view/Carregar.fxml");
    }

    /**
     * Trata o evento de clique no botão de login
     * 
     * @param event evento de clique
     */
    @FXML
    private void loginButtonOnAction(ActionEvent event) {
        try {
            Tools.actionTeste(event, "/com/example/lp3_grupo1/view/Main.fxml", "Subway2Feira", 1280, 650);
        } catch (IOException e) {
            Tools.mostrarAlerta(Alert.AlertType.ERROR, "Erro", "Não foi possível fazer Logout.");
            e.printStackTrace();
        }
    }
}
