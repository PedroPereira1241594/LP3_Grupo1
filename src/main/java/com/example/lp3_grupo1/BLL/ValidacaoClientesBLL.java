package com.example.lp3_grupo1.BLL;

import java.util.List;

import com.example.lp3_grupo1.Model.Utilizador;
import com.example.lp3_grupo1.DAL.UtilizadorDAL;

public class ValidacaoClientesBLL {
    public List<Utilizador> listar() {
        UtilizadorDAL utilizadorDAL = new UtilizadorDAL();
        return utilizadorDAL.getAll();
    }
}
