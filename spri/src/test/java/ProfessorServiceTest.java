import com.example.controller.ProfessorController;
import com.example.model.Professor;
import com.example.service.ProfessorService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProfessorServiceTest {

    private ProfessorController professorController;
    private ProfessorService professorService;

    @BeforeEach
    void setUp() {
        professorController = mock(ProfessorController.class); // Mock do ProfessorController
        professorService = new ProfessorService(); // Injetando o mock no serviço
    }



    @Test
    void validarLogin_Invalido() {
        String cpf = null;
        String senha = null;

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                professorService.validarLogin(cpf, senha));

        assertEquals("CPF e senha não podem ser nulos.", exception.getMessage());
    }


    @Test
    void getIdProfessor_CpfNulo() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                professorService.getIdProfessor(null));

        assertEquals("CPF não pode ser nulo.", exception.getMessage());
    }


    @Test
    void buscarProfessorPorId_Invalido() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                professorService.buscarProfessorPorId(-1));

        assertEquals("ID inválido.", exception.getMessage());
    }
}
