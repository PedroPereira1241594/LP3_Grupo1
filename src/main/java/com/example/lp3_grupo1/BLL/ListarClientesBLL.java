package com.example.lp3_grupo1.BLL;

import com.example.lp3_grupo1.DAL.UtilizadorDAL;
import com.example.lp3_grupo1.Model.Utilizador;

import java.util.List;

public class ListarClientesBLL {

    public List<Utilizador> listar() {
        UtilizadorDAL utilizadorDAL = new UtilizadorDAL();
        return utilizadorDAL.getAll();
    }
}
