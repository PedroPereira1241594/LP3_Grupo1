package com.example.lp3_grupo1.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.Validator;

public class backOfficeController {

    @FXML private TextField xmlPathField;
    @FXML private TextField xsdPathField;
    @FXML private Button validateButton;
    @FXML private Label statusLabel;

    private File xmlFile;
    private File xsdFile;

    //region XML/XSD Loading and Validation
    /**
     * Abre uma janela para selecionar um ficheiro XML e atualiza o campo de caminho.
     * Atualiza também o estado do botão de validação e o label de status.
     */
    @FXML
    private void onLoadXml() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecionar ficheiro XML");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Ficheiros XML", "*.xml"));
        File file = fileChooser.showOpenDialog(new Stage());
        if (file != null) {
            xmlFile = file;
            xmlPathField.setText(file.getAbsolutePath());
            updateValidateButton();
            statusLabel.setText("Ficheiro XML carregado com sucesso.");
        }
    }

    /**
     * Abre uma janela para selecionar um ficheiro XSD e atualiza o campo de caminho.
     * Atualiza também o estado do botão de validação e o label de status.
     */
    @FXML
    private void onLoadXsd() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecionar ficheiro XSD");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Ficheiros XSD", "*.xsd"));
        File file = fileChooser.showOpenDialog(new Stage());
        if (file != null) {
            xsdFile = file;
            xsdPathField.setText(file.getAbsolutePath());
            updateValidateButton();
            statusLabel.setText("Ficheiro XSD carregado com sucesso.");
        }
    }

    /**
     * Atualiza o estado do botão de validação, desativando-o se os ficheiros XML ou XSD não estiverem carregados.
     */
    private void updateValidateButton() {
        validateButton.setDisable(xmlFile == null || xsdFile == null);
    }

    /**
     * Valida o ficheiro XML carregado contra o ficheiro XSD selecionado.
     * Atualiza o label de status com o resultado da validação.
     */
    @FXML
    private void onValidate() {
        if (xmlFile != null && xsdFile != null) {
            try {
                statusLabel.setText("A validar " + xmlFile.getName() + " com " + xsdFile.getName() + "...");

                // Criar factory de schema
                SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
                Schema schema = factory.newSchema(xsdFile);
                Validator validator = schema.newValidator();

                // Validar XML
                validator.validate(new StreamSource(xmlFile));

                statusLabel.setText("Validação concluída com sucesso! O XML é válido.");
            } catch (Exception e) {
                statusLabel.setText("Erro na validação: " + e.getMessage());
            }
        } else {
            statusLabel.setText("Por favor, carregue ambos os ficheiros antes de validar.");
        }
    }
    //endregion
}
