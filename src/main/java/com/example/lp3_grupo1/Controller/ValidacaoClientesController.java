package com.example.lp3_grupo1.Controller;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import javafx.beans.binding.Bindings;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import com.example.lp3_grupo1.Model.Utilizador;
import com.example.lp3_grupo1.BLL.ValidacaoClientesBLL;

/**
 * Controlador da vista de validação de clientes.
 * Responsável por mapear as colunas da tabela, carregar dados pendentes,
 * e disponibilizar ações de aprovação individual e em lote.
 */
public class ValidacaoClientesController {
    @FXML
    private TableView<Utilizador> tabelaPessoas;

    @FXML
    private Button btnAceitarTodos;

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
    private TableColumn<Utilizador, Void> colAceitar;

    /**
     * Inicializa a tabela, configura o mapeamento de colunas, carrega os dados
     * e define o estado do botão "Aceitar todos" conforme existência de itens.
     */
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

        configurarColunaAceitar();

        // Carregar dados através da BLL
        ValidacaoClientesBLL validacaoClientesBLL = new ValidacaoClientesBLL();
        List<Utilizador> dados = validacaoClientesBLL.listar();
        tabelaPessoas.setItems(FXCollections.observableArrayList(dados));

        if (btnAceitarTodos != null) {
            btnAceitarTodos.disableProperty().bind(Bindings.isEmpty(tabelaPessoas.getItems()));
        }
    }

    /**
     * Configura a coluna de ações para renderizar um botão "Aceitar" em cada linha,
     * que ao ser clicado aprova o respetivo utilizador.
     */
    private void configurarColunaAceitar() {
        if (colAceitar == null) return;

        Callback<TableColumn<Utilizador, Void>, TableCell<Utilizador, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Utilizador, Void> call(final TableColumn<Utilizador, Void> param) {
                return new TableCell<>() {
                    private final Button btn = new Button("Aceitar");

                    {
                        btn.setOnAction(event -> {
                            Utilizador item = getTableView().getItems().get(getIndex());
                            onAceitar(item);
                        });
                        btn.setMaxWidth(Double.MAX_VALUE);
                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
            }
        };
        colAceitar.setCellFactory(cellFactory);
    }

    /**
     * Aprova um único utilizador e remove-o da tabela.
     *
     * @param utilizador utilizador a aprovar.
     */
    private void onAceitar(Utilizador utilizador) {
        ValidacaoClientesBLL validacaoClientesBLL = new ValidacaoClientesBLL();
        validacaoClientesBLL.aprovarCliente(utilizador);
        tabelaPessoas.getItems().remove(utilizador);
    }

    /**
     * Solicita confirmação e, se aceite, aprova todos os utilizadores presentes
     * na tabela, limpando a listagem no final e apresentando feedback.
     */
    @FXML
    private void onAceitarTodos() {
        List<Utilizador> itens = List.copyOf(tabelaPessoas.getItems());
        if (itens.isEmpty()) return;

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Pretende aprovar todos os utilizadores listados?", ButtonType.YES, ButtonType.NO);
        confirm.setHeaderText(null);
        ButtonType result = confirm.showAndWait().orElse(ButtonType.NO);
        if (result != ButtonType.YES) return;

        ValidacaoClientesBLL validacaoClientesBLL = new ValidacaoClientesBLL();
        for (Utilizador u : itens) {
            validacaoClientesBLL.aprovarCliente(u);
        }
        tabelaPessoas.getItems().clear();

        Alert info = new Alert(Alert.AlertType.INFORMATION, "Utilizadores aprovados com sucesso.", ButtonType.OK);
        info.setHeaderText(null);
        info.showAndWait();
    }
}
