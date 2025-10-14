package com.example.lp3_grupo1.Controller;

import com.example.lp3_grupo1.BLL.ListarClientesBLL;
import com.example.lp3_grupo1.Model.Utilizador;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

/**
 * Controlador responsável pela interface de listagem de clientes.
 * <p>
 * Esta classe faz a ligação entre a camada de apresentação (FXML)
 * e a camada de lógica de negócio ({@link ListarClientesBLL}), sendo
 * responsável por inicializar e preencher a {@link TableView} com
 * dados dos clientes registados.
 * </p>
 *
 * <p>
 * Através do método {@link #initialize()}, as colunas da tabela são configuradas
 * e os dados são carregados dinamicamente a partir da base de dados.
 * </p>
 */
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
     * Inicializa a tabela de clientes e carrega os dados no momento
     * em que o FXML é renderizado.
     * <p>
     * Este método:
     * <ul>
     *     <li>Configura as colunas da {@link TableView} com as propriedades da classe {@link Utilizador};</li>
     *     <li>Chama a camada {@link ListarClientesBLL} para obter todos os utilizadores registados;</li>
     *     <li>Insere os dados obtidos na tabela.</li>
     * </ul>
     * </p>
     *
     * <p>
     * Este método é automaticamente invocado pelo JavaFX após o carregamento do FXML.
     * </p>
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

        // Inserir os dados na tabela
        tabelaPessoas.setItems(FXCollections.observableArrayList(dados));
    }

}
