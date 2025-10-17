package com.example.lp3_grupo1.Controller;

import java.io.IOException;

import com.example.Utils.Tools;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

public class MainController {

    @FXML
    private void loginButtonOnAction(ActionEvent event) {
        try {
            Tools.actionTeste(event, "/com/example/lp3_grupo1/view/Login.fxml", "Subway2Feira", 1280, 650);
        } catch (IOException e) {
            Tools.mostrarAlerta(Alert.AlertType.ERROR, "Erro", "Não foi possível carregar a página de Login.");
            e.printStackTrace();
        }
    }

    @FXML
    private void closeApplicationButtonOnAction(ActionEvent event) {
        // ação do botão Logout
    }

    @FXML
    private void downloadButtonOnAction(ActionEvent event) {
        // ação para gerir estações
    }

}