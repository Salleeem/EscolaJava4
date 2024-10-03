package com.example.controller;

import com.example.model.Materia;
import com.example.repository.MateriaRepository;
import java.sql.Connection;

public class MateriaController {
    
    private MateriaRepository materiaRepository;

    public MateriaController(Connection connection) {
        this.materiaRepository = new MateriaRepository(connection);
    }

    public void cadastrarMateria(Materia materia) {
        materiaRepository.cadastrarMateria(materia);
    }
}
