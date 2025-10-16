package com.example.lp3_grupo1.Controller;

import com.example.Utils.Tools;
import com.example.lp3_grupo1.BLL.ListarClientesBLL;
import com.example.lp3_grupo1.Model.Utilizador;
import com.example.Utils.Tools.estadoUtilizador;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Controlador da página de listagem de clientes.
 * <p>
 * Esta classe é responsável por:
 * <ul>
 *     <li>Configurar as colunas da tabela de utilizadores;</li>
 *     <li>Carregar os dados através da camada BLL;</li>
 *     <li>Permitir alterar o estado de cada utilizador diretamente na tabela (dropdown);</li>
 *     <li>Detetar quais utilizadores tiveram o estado modificado, comparando com o estado inicial;</li>
 *     <li>Guardar as alterações e mostrar mensagens de sucesso ou erro ao utilizador.</li>
 * </ul>
 * </p>
 */
public class ListagemClienteController {

    @FXML private TableView<Utilizador> tabelaPessoas;
    @FXML private TableColumn<Utilizador, Integer> colId;
    @FXML private TableColumn<Utilizador, String> colNome;
    @FXML private TableColumn<Utilizador, String> colPassword;
    @FXML private TableColumn<Utilizador, String> colEmail;
    @FXML private TableColumn<Utilizador, java.time.LocalDate> colDataNascimento;
    @FXML private TableColumn<Utilizador, estadoUtilizador> colEstado;
    @FXML private TableColumn<Utilizador, Integer> colTipo;
    @FXML private Button btnGuardarAlteracoes;

    /**
     * Guarda o estado inicial de cada utilizador no momento em que os dados são carregados.
     * A chave é o ID do utilizador e o valor é o estado (int).
     * Isto permite verificar mais tarde se houve alguma alteração.
     */
    private final Map<Integer, Integer> estadoInicialPorId = new HashMap<>();

    /**
     * Método que é chamado automaticamente quando a interface é carregada.
     * Aqui são feitas as seguintes ações:
     * <ul>
     *     <li>Ativar edição da tabela e da coluna Estado;</li>
     *     <li>Definir como cada coluna vai exibir os dados (value factories);</li>
     *     <li>Transformar a coluna Estado num dropdown com os valores do enum {@link estadoUtilizador};</li>
     *     <li>Ativar o botão "Guardar" quando alguma célula for editada;</li>
     *     <li>Carregar os utilizadores e guardar o estado inicial de cada um.</li>
     * </ul>
     */
    @FXML
    private void initialize() {

        tabelaPessoas.setEditable(true);
        colEstado.setEditable(true);

        colId.setCellValueFactory(c -> new ReadOnlyObjectWrapper<>(c.getValue().getId()));
        colNome.setCellValueFactory(c -> new ReadOnlyObjectWrapper<>(c.getValue().getNome()));
        colPassword.setCellValueFactory(c -> new ReadOnlyObjectWrapper<>(c.getValue().getPassword()));
        colEmail.setCellValueFactory(c -> new ReadOnlyObjectWrapper<>(c.getValue().getEmail()));
        colDataNascimento.setCellValueFactory(c -> new ReadOnlyObjectWrapper<>(c.getValue().getDataNascimento()));
        colTipo.setCellValueFactory(c -> new ReadOnlyObjectWrapper<>(c.getValue().getTipo()));

        // Mostra o nome do estado (enum) na tabela em vez do número
        colEstado.setCellValueFactory(cd ->
                new ReadOnlyObjectWrapper<>(estadoUtilizador.fromCodigo(cd.getValue().getEstado()))
        );

        // Transforma a célula em um dropdown (ComboBox)
        colEstado.setCellFactory(ComboBoxTableCell.forTableColumn(estadoUtilizador.values()));

        // Quando o utilizador altera o estado, atualiza o modelo e ativa o botão
        colEstado.setOnEditCommit(e -> {
            Utilizador u = e.getRowValue();
            estadoUtilizador novo = e.getNewValue();
            if (novo != null) {
                int codigoNovo = novo.getCodigo();
                if (u.getEstado() != codigoNovo) {
                    u.setEstado(codigoNovo);
                }
                btnGuardarAlteracoes.setDisable(false);
                tabelaPessoas.refresh();
            }
        });

        // Qualquer edição em qualquer coluna ativa o botão Guardar
        tabelaPessoas.addEventHandler(TableColumn.CellEditEvent.ANY, e -> {
            if (btnGuardarAlteracoes != null) btnGuardarAlteracoes.setDisable(false);
        });

        List<Utilizador> dados = new ListarClientesBLL().listar();
        tabelaPessoas.setItems(FXCollections.observableArrayList(dados));

        // Guarda o estado inicial de cada utilizador (antes de qualquer alteração)
        estadoInicialPorId.clear();
        for (Utilizador u : dados) {
            estadoInicialPorId.put(u.getId(), u.getEstado());
        }

        // O botão começa desativado até que alguma alteração seja feita
        btnGuardarAlteracoes.setDisable(true);
    }

    /**
     * Ação do botão "Guardar alterações".
     * <p>
     * Este método:
     * <ul>
     *     <li>Compara o estado atual de cada utilizador com o estado inicial;</li>
     *     <li>Filtra apenas os que tiveram alterações;</li>
     *     <li>Chama a BLL para atualizar a base de dados;</li>
     *     <li>Atualiza o estado inicial para refletir as mudanças guardadas;</li>
     *     <li>Mostra uma mensagem de sucesso ou erro ao utilizador.</li>
     * </ul>
     * </p>
     */
    @FXML
    private void onGuardarAlteracoes() {
        ListarClientesBLL bll = new ListarClientesBLL();

        // Verifica quais utilizadores tiveram alteração no estado
        List<Utilizador> alterados = tabelaPessoas.getItems().stream()
                .filter(u -> !Objects.equals(estadoInicialPorId.get(u.getId()), u.getEstado()))
                .toList();

        // Se não houve alterações, mostra mensagem e sai
        if (alterados.isEmpty()) {
            Tools.mostrarAlerta(Alert.AlertType.INFORMATION, "Sem alterações", "Não existem alterações para guardar.");
            return;
        }

        // Atualiza cada utilizador alterado
        boolean sucesso = true;
        for (Utilizador u : alterados) {
            sucesso &= bll.atualizarEstado(u.getId(), u.getEstado());
        }

        // Se tudo correu bem, atualiza o estado inicial e desativa o botão novamente
        if (sucesso) {
            alterados.forEach(u -> estadoInicialPorId.put(u.getId(), u.getEstado()));
            btnGuardarAlteracoes.setDisable(true);
            Tools.mostrarAlerta(Alert.AlertType.INFORMATION, "Sucesso", "Alterações guardadas com sucesso!");
        } else {
            Tools.mostrarAlerta(Alert.AlertType.ERROR, "Erro", "Algumas alterações não foram guardadas.");
        }
    }
}
