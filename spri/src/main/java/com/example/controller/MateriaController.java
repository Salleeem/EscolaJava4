package com.example.controller;

import com.example.model.Materia;
import com.example.repository.MateriaRepository;

import java.sql.Connection;
import java.util.List;

public class MateriaController {
    
    private MateriaRepository materiaRepository;

    public MateriaController(Connection connection) {
        this.materiaRepository = new MateriaRepository(connection);
    }

    // Método para cadastrar uma nova matéria
    public void cadastrarMateria(Materia materia) {
        materiaRepository.cadastrarMateria(materia);
    }

    // Método para buscar todas as matérias cadastradas
    public List<Materia> buscarTodasAsMaterias() {
        return materiaRepository.buscarTodasAsMaterias();
    }
}
