package com.example.Utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;

import java.io.IOException;
import java.net.URL;

public class Tools {
    public enum tipoUtilizador {
        CLIENTE(1), GESTOR(2);

        private final int codigo;

        tipoUtilizador(int codigo) {
            this.codigo = codigo;
        }

        public int getCodigo() {
            return codigo;
        }

        public static tipoUtilizador fromCodigo(int codigo) {
            for (tipoUtilizador tipo : tipoUtilizador.values()) {
                if (tipo.getCodigo() == codigo) {
                    return tipo;
                }
            }
            throw new IllegalArgumentException("Código inválido: " + codigo);
        }
    }

    public enum estadoUtilizador {
        DEFAULT(0), PENDENTE(1), ATIVO(2), INATIVO(3);

        private final int codigo;

        estadoUtilizador(int codigo) {
            this.codigo = codigo;
        }

        public int getCodigo() {
            return codigo;
        }

        public static estadoUtilizador fromCodigo(int codigo) {
            for (estadoUtilizador estado : estadoUtilizador.values()) {
                if (estado.getCodigo() == codigo) {
                    return estado;
                }
            }
            throw new IllegalArgumentException("Código inválido: " + codigo);
        }

        public static estadoUtilizador getDefault() {
            return DEFAULT; // Valor Default do enum estadoUtilizador
        }

    }

    /**
     * Exibe uma janela de alerta personalizada ao utilizador.
     * <p>
     * Este método permite mostrar mensagens informativas, de erro, aviso ou confirmação
     * através de uma {@link javafx.scene.control.Alert}, ajustando automaticamente
     * a largura e altura da caixa de diálogo para uma melhor legibilidade do texto.
     * </p>
     *
     * @param tipo     o tipo de alerta a ser exibido (ex.: {@link javafx.scene.control.Alert.AlertType#INFORMATION}, {@link javafx.scene.control.Alert.AlertType#ERROR})
     * @param titulo   o título da janela de alerta
     * @param mensagem o texto da mensagem a ser apresentada ao utilizador
     */
    public static void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensagem) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);

        Label label = new Label(mensagem);
        label.setWrapText(true);

        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.setContent(label);
        dialogPane.setMinHeight(Region.USE_PREF_SIZE);
        dialogPane.setPrefWidth(450);
        dialogPane.setPrefHeight(Region.USE_COMPUTED_SIZE);

        alert.showAndWait();
    }

    /**
     * Carrega e substitui dinamicamente o conteúdo de um {@link javafx.scene.layout.AnchorPane}
     * com a interface definida num ficheiro FXML.
     * <p>
     * Este método procura o FXML no caminho fornecido, carrega-o e
     * insere o novo conteúdo no painel principal, ajustando automaticamente
     * as âncoras para que ocupe toda a área disponível.
     * Em caso de erro, apresenta uma mensagem de alerta ao utilizador.
     * </p>
     *
     * @param apMain       o painel principal ({@link AnchorPane}) onde o novo conteúdo será inserido
     * @param caminhoFxml  o caminho relativo para o ficheiro FXML a ser carregado (por exemplo: {@code "/views/Exemplo.fxml"})
     */
    public static void carregarConteudo(AnchorPane apMain, String caminhoFxml) {
        try {
            URL fxmlUrl = Tools.class.getResource(caminhoFxml);
            if (fxmlUrl == null) {
                mostrarAlerta(Alert.AlertType.ERROR, "Erro", "FXML não encontrado:\n" + caminhoFxml);
                return;
            }

            Node conteudo = FXMLLoader.load(fxmlUrl);

            AnchorPane.setTopAnchor(conteudo, 0.0);
            AnchorPane.setRightAnchor(conteudo, 0.0);
            AnchorPane.setBottomAnchor(conteudo, 0.0);
            AnchorPane.setLeftAnchor(conteudo, 0.0);

            apMain.getChildren().setAll(conteudo);

        } catch (IOException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Erro ao carregar vista", "Não foi possível carregar o ecrã:\n" + caminhoFxml);
            e.printStackTrace();
        }
    }

}
