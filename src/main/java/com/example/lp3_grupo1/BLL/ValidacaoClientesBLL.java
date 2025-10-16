package com.example.lp3_grupo1.BLL;

import java.util.List;

import com.example.lp3_grupo1.Model.Utilizador;
import com.example.lp3_grupo1.DAL.UtilizadorDAL;

/**
 * Camada de Lógica de Negócio (BLL) para operações de validação de clientes.
 * Fornece métodos para listar utilizadores pendentes e aprovar um utilizador.
 */
public class ValidacaoClientesBLL {
    /**
     * Obtém a lista de utilizadores com estado pendente de aprovação.
     *
     * @return lista de {@link Utilizador} pendentes.
     */
    public List<Utilizador> listar() {
        UtilizadorDAL utilizadorDAL = new UtilizadorDAL();
        return utilizadorDAL.getPendentes();
    }

    /**
     * Aprova o utilizador indicado, atualizando o seu estado na base de dados.
     *
     * @param u utilizador a aprovar.
     */
    public void aprovarCliente(Utilizador u) {
        UtilizadorDAL utilizadorDAL = new UtilizadorDAL();
        utilizadorDAL.AprovarCliente(u);
    }
}
