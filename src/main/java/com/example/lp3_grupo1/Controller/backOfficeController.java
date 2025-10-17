package com.example.lp3_grupo1.Controller;

import com.example.lp3_grupo1.BLL.backOfficeBLL;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.xml.validation.Validator;
import java.io.File;

/**
 * Controller responsável pela interface do carregarXMLView no JavaFX.
 *
 * <p>Este controller permite:
 * <ul>
 *     <li>Carregar ficheiros XML e XSD através de janelas de ficheiros.</li>
 *     <li>Validar o XML em relação ao XSD.</li>
 *     <li>Processar o XML e inserir Linhas, Estações e Viagens na base de dados.</li>
 *     <li>Atualizar o estado da interface com mensagens de sucesso ou erro.</li>
 * </ul>
 * </p>
 */
public class backOfficeController {

    @FXML
    private TextField xmlPathField;

    @FXML
    private TextField xsdPathField;

    @FXML
    private Button validateButton;

    @FXML
    private Label statusLabel;

    private File xmlFile;
    private File xsdFile;

    private final backOfficeBLL bll = new backOfficeBLL();

    /**
     * Abre uma janela para seleção de ficheiro XML.
     * Atualiza o campo de caminho, ativa/desativa o botão de validação e mostra uma mensagem de estado na interface.
     */
    @FXML
    private void onLoadXml() {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Selecionar ficheiro XML");
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Ficheiros XML", "*.xml"));
        File file = chooser.showOpenDialog(new Stage());
        if (file != null) {
            xmlFile = file;
            xmlPathField.setText(file.getAbsolutePath());
            updateValidateButton();
            statusLabel.setText("Ficheiro XML carregado com sucesso.");
        }
    }

    /**
     * Abre uma janela para seleção de ficheiro XSD.
     * Atualiza o campo de caminho, ativa/desativa o botão de validação e mostra uma mensagem de estado na interface.
     */
    @FXML
    private void onLoadXsd() {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Selecionar ficheiro XSD");
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Ficheiros XSD", "*.xsd"));
        File file = chooser.showOpenDialog(new Stage());
        if (file != null) {
            xsdFile = file;
            xsdPathField.setText(file.getAbsolutePath());
            updateValidateButton();
            statusLabel.setText("Ficheiro XSD carregado com sucesso.");
        }
    }

    /**
     * Atualiza o estado do botão de validação.
     * O botão é desativado se não existirem ficheiros XML ou XSD carregados.
     */
    private void updateValidateButton() {
        validateButton.setDisable(xmlFile == null || xsdFile == null);
    }

    /**
     * Valida o XML carregado em relação ao XSD.
     * Se a validação for bem-sucedida, processa o XML e insere os dados na base de dados.
     * Atualiza a statusLabel com mensagens de progresso ou erro.
     */
    @FXML
    private void onValidate() {
        if (xmlFile == null || xsdFile == null) {
            statusLabel.setText("Por favor, carregue ambos os ficheiros antes de validar.");
            return;
        }

        try {
            statusLabel.setText("A validar XML...");
            Validator validator = bll.createValidator(xsdFile);
            bll.validateXml(xmlFile, xsdFile, validator);
            statusLabel.setText("Validação concluída! Processando XML...");
            bll.processXml(xmlFile);
            statusLabel.setText("XML processado e inserido com sucesso!");
            cleanForm();
        } catch (Exception e) {
            statusLabel.setText("Erro: " + e.getMessage());
        }
    }

    /**
     * Limpa o form da interface:
     * <ul>
     *     <li>Limpa os campos de caminho XML e XSD.</li>
     *     <li>Reset ás variáveis de ficheiro xmlFile e xsdFile.</li>
     *     <li>Atualiza o estado do botão de validação.</li>
     * </ul>
     */
    private void cleanForm() {
        xmlPathField.clear();
        xsdPathField.clear();
        xmlFile = null;
        xsdFile = null;
        updateValidateButton();
    }
}
