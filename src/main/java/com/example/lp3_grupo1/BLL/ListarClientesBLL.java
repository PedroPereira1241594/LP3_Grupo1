package com.example.lp3_grupo1.BLL;

import com.example.lp3_grupo1.DAL.UtilizadorDAL;
import com.example.lp3_grupo1.Model.Utilizador;

import java.util.List;

/**
 * Classe responsável pela lógica de negócio (BLL) relacionada com a listagem de clientes.
 * Esta classe atua como intermediária entre a camada de apresentação (UI/Controller)
 * e a camada de acesso a dados (DAL), garantindo uma melhor separação de responsabilidades.
 */
public class ListarClientesBLL {

    private final UtilizadorDAL utilizadorDAL;

    public ListarClientesBLL() {
        this.utilizadorDAL = new UtilizadorDAL();
    }

    /**
     * Obtém a lista de todos os utilizadores registados no sistema.
     */
    public List<Utilizador> listar() {
        return utilizadorDAL.getAll();
    }

    /**
     * Atualiza o estado de um utilizador na base de dados.
     *
     * @param idUtilizador  ID do utilizador a atualizar
     * @param codigoEstado  Código numérico correspondente ao novo estado
     * @return true se a atualização foi bem-sucedida, false caso contrário
     */
    public boolean atualizarEstado(int idUtilizador, int codigoEstado) {
        return utilizadorDAL.atualizarEstado(idUtilizador, codigoEstado);
    }
}
