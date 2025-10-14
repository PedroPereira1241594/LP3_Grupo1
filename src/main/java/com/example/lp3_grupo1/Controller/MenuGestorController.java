package com.example.lp3_grupo1.Controller;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import com.example.Utils.Tools;


public class MenuGestorController {

    @FXML
    private AnchorPane contentBox; // <-- isto liga ao fx:id="contentBox" do FXML base



    @FXML
    private void onListarClientes() {
        Tools.carregarConteudo(contentBox, "/com/example/lp3_grupo1/view/ListagemCliente.fxml");
    }


    @FXML
    private void onGestaoClientes() {
        Tools.carregarConteudo(contentBox, "/com/example/lp3_grupo1/view/GestaoCliente.fxml");
    }


    @FXML
    private void onValidarClientes() {
        Tools.carregarConteudo(contentBox, "/com/example/lp3_grupo1/view/ValidacaoClientes.fxml");
    }

    @FXML
    private void onInserirMapa() {
        Tools.carregarConteudo(contentBox, "/com/example/lp3_grupo1/view/Carregar.fxml");
    }

    @FXML
    private void loginButtonOnAction() {
        // lógica de logout/sair (se precisares)
    }
}
