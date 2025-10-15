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

    /**
     * Obtém a lista de todos os utilizadores registados no sistema.
     * <p>
     * Este método invoca a camada de acesso a dados {@link UtilizadorDAL}
     * para recuperar todos os registos existentes na base de dados.
     * Pode ser utilizada pela camada de controlo para apresentar os dados
     * numa tabela ou lista na interface gráfica.
     * </p>
     *
     * @return uma {@link List} de objetos {@link Utilizador} que representam os clientes registados
     */
    public List<Utilizador> listar() {
        UtilizadorDAL utilizadorDAL = new UtilizadorDAL();
        return utilizadorDAL.getAll();
    }
}
