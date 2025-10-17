package com.example.lp3_grupo1.BLL;

import com.example.lp3_grupo1.DAL.backOfficeDAL;
import com.example.lp3_grupo1.Model.Estacao;
import com.example.lp3_grupo1.Model.Linha;
import com.example.lp3_grupo1.Model.Viagem;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.Validator;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Classe responsável pela lógica de negócio do BackOffice.
 * Inclui validação de XML, parsing e inserção de dados na base de dados.
 */
public class backOfficeBLL {

    private final backOfficeDAL dal;

    /**
     * Construtor da classe backOfficeBLL.
     * Inicializa a instância da camada de acesso a dados (DAL).
     */
    public backOfficeBLL() {
        dal = new backOfficeDAL();
    }

    /**
     * Valida um ficheiro XML em relação a um XSD utilizando um Validator.
     *
     * @param xmlFile   O ficheiro XML a validar.
     * @param xsdFile   O ficheiro XSD utilizado para validação (não é usado diretamente, mas serve para referência).
     * @param validator Instância de Validator configurada com o XSD.
     * @throws Exception Lança exceção se a validação falhar.
     */
    public void validateXml(File xmlFile, File xsdFile, Validator validator) throws Exception {
        validator.validate(new StreamSource(xmlFile));
    }

    /**
     * Cria um Validator a partir de um ficheiro XSD.
     *
     * @param xsdFile Ficheiro XSD utilizado para criar o Validator.
     * @return Instância de Validator pronta para validar ficheiros XML.
     * @throws Exception Lança exceção se houver problemas na criação do Validator.
     */
    public Validator createValidator(File xsdFile) throws Exception {
        Schema schema = javax.xml.validation.SchemaFactory
                .newInstance(javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI)
                .newSchema(xsdFile);
        return schema.newValidator();
    }

    /**
     * Faz o parsing de um ficheiro XML, extrai Linhas, Estações e Viagens,
     * e insere os dados processados na base de dados utilizando a DAL.
     * <p>
     * O método realiza as seguintes operações:
     * <ul>
     *     <li>Criação de objetos Linha a partir do XML.</li>
     *     <li>Criação de objetos Estacao e associação às Linhas correspondentes.</li>
     *     <li>Criação de objetos Viagem e associação a Estacoes e Linhas.</li>
     * </ul>
     *
     * @param xmlFile Ficheiro XML a processar.
     * @throws Exception Lança exceção se ocorrer qualquer erro durante o parsing ou inserção na base de dados.
     */
    public void processXml(File xmlFile) throws Exception {
        Map<String, Linha> linesMap = new HashMap<>();
        Map<String, Estacao> stationsMap = new HashMap<>();
        List<Viagem> tripsList = new ArrayList<>();

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlFile);
        doc.getDocumentElement().normalize();

        // Linhas
        NodeList lineNodes = doc.getElementsByTagName("Lines").item(0).getChildNodes();
        for (int i = 0; i < lineNodes.getLength(); i++) {
            if (lineNodes.item(i) instanceof Element e && e.getTagName().equals("Line")) {
                String key = e.getAttribute("Key");
                NodeList nameNodes = e.getElementsByTagName("Name");
                if (nameNodes.getLength() == 0) continue;
                linesMap.put(key, new Linha(key, nameNodes.item(0).getTextContent()));
            }
        }

        // Estações
        NodeList stationNodes = doc.getElementsByTagName("Stations").item(0).getChildNodes();
        int stationCounter = 1;
        for (int i = 0; i < stationNodes.getLength(); i++) {
            if (stationNodes.item(i) instanceof Element sElem && sElem.getTagName().equals("Station")) {
                NodeList nameNodes = sElem.getElementsByTagName("Name");
                if (nameNodes.getLength() == 0) continue;
                Estacao estacao = new Estacao(stationCounter++, nameNodes.item(0).getTextContent());

                NodeList lineRefs = sElem.getElementsByTagName("Lines");
                if (lineRefs.getLength() > 0) {
                    NodeList lineList = ((Element) lineRefs.item(0)).getElementsByTagName("Line");
                    for (int j = 0; j < lineList.getLength(); j++) {
                        Linha linha = linesMap.get(lineList.item(j).getTextContent());
                        if (linha != null) {
                            estacao.addLinha(linha);
                            linha.addEstacao(estacao);
                        }
                    }
                }

                stationsMap.put(estacao.getNome(), estacao);
            }
        }

        // Viagens
        NodeList tripNodes = doc.getElementsByTagName("Trips").item(0).getChildNodes();
        int tripCounter = 1;
        for (int i = 0; i < tripNodes.getLength(); i++) {
            if (tripNodes.item(i) instanceof Element tElem && tElem.getTagName().equals("Trip")) {
                NodeList depNodes = tElem.getElementsByTagName("Departure");
                NodeList arrNodes = tElem.getElementsByTagName("Arrival");
                NodeList lineNodesTrip = tElem.getElementsByTagName("Line");
                if (depNodes.getLength() == 0 || arrNodes.getLength() == 0 || lineNodesTrip.getLength() == 0)
                    continue;

                Estacao depEstacao = stationsMap.get(depNodes.item(0).getTextContent());
                Estacao arrEstacao = stationsMap.get(arrNodes.item(0).getTextContent());
                Linha linha = linesMap.get(lineNodesTrip.item(0).getTextContent());

                if (depEstacao != null && arrEstacao != null && linha != null) {
                    tripsList.add(new Viagem(tripCounter++, depEstacao, arrEstacao, linha));
                }
            }
        }

        dal.insertData(linesMap.values(), stationsMap.values(), tripsList);
    }
}
