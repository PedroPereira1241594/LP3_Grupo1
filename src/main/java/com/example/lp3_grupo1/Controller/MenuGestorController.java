package com.example.lp3_grupo1.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MenuGestorController {

    @FXML
    public void initialize() {
        Tools.carregarConteudo(contentBox, "/fxml/MapaAtual.fxml"); // ecrã inicial
    }

    @FXML private void onListarClientes()  { Tools.carregarConteudo(contentBox, "/fxml/ListarClientes.fxml"); }
    @FXML private void onGestaoClientes()  { Tools.carregarConteudo(contentBox, "/fxml/GestaoClientes.fxml"); }
    @FXML private void onValidarClientes() { Tools.carregarConteudo(contentBox, "/fxml/ValidacaoClientes.fxml"); }
    @FXML private void onInserirMapa()     { Tools.carregarConteudo(contentBox, "/fxml/InserirMapa.fxml"); }


}
