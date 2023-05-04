package com.example.lab_0405.domain;

import java.util.LinkedList;
import java.util.List;

public class Departamento {
    private List<Funcionario> funcionarios = new LinkedList<Funcionario>();

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }


}
