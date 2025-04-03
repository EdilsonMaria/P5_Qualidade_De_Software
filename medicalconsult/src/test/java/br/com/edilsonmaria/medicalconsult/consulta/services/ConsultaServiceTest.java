package br.com.edilsonmaria.medicalconsult.consulta.services;

import br.com.edilsonmaria.medicalconsult.consulta.domain.Consulta;
import br.com.edilsonmaria.medicalconsult.consulta.repository.ConsultaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ConsultaServiceTest {

    @InjectMocks
    private ConsultaService consultaService;

    @Mock
    private ConsultaRepository consultaRepository;

    @Test
    void cadastrarConsulta() {
        Consulta consulta = new Consulta();
        consulta.setEspecialidade("Cardiologia");
        //configuração de componente em Mock
        when(consultaRepository.save(any(Consulta.class))).thenReturn(consulta);

        //a execução do metodo a ser testado
        var resultado = consultaService.cadastrarConsulta(consulta);

        //validação do teste
        assertNotNull(resultado);
        assertEquals("Cardiologia", resultado.getEspecialidade());

        verify(consultaRepository, times(1)).save(consulta);
    }

    @Test
    void listarConsultas() {
        //criando o objeto consulta
        Consulta consulta1 = new Consulta();
        consulta1.setEspecialidade("Cardiologia");

        Consulta consulta2 = new Consulta();
        consulta2.setEspecialidade("Dermatologia");

        List<Consulta> consultas = new ArrayList<>();
        consultas.add(consulta1);
        consultas.add(consulta2);

        //configuração de componente em Mock
        when(consultaRepository.findAll()).thenReturn(consultas);

        var resultado = consultaService.listarConsultas();

        //validação do teste
        assertAll(
                () -> assertNotNull(resultado),
                () -> assertEquals(2, resultado.size()),
                () -> assertEquals("Cardiologia", resultado.get(0).getEspecialidade()),
                () -> assertEquals("Dermatologia", resultado.get(1).getEspecialidade())
        );
    }

    @Test
    void buscarConsulta() {
        //criando o objeto consulta
        Consulta consulta = new Consulta();
        consulta.setIdConsulta(1L);
        consulta.setEspecialidade("Cardiologia");

        //configuração de componente em Mock
        when(consultaRepository.findById(1L)).thenReturn(Optional.of(consulta));

        //a execução do metodo a ser testado
        var resultado = consultaService.buscarConsulta(1L);

        //validação do teste
        assertNotNull(resultado);
        assertEquals("Cardiologia", resultado.getEspecialidade());

        verify(consultaRepository, times(1)).findById(1L);
    }

    @Test
    void atualizarConsulta() {
        // Dados do consulta existente e do consulta atualizado
        Consulta consultaExistente = new Consulta();
        consultaExistente.setEspecialidade("Cardiologia");

        Consulta consultaAtualizada = new Consulta();
        consultaAtualizada.setEspecialidade("Ortopedia");

        // Configuração do mock
        when(consultaRepository.findById(1L)).thenReturn(Optional.of(consultaExistente));
        when(consultaRepository.save(any(Consulta.class))).thenReturn(consultaExistente);

        // Execução do método
        var resultado = consultaService.atualizarConsulta(consultaAtualizada);

        //validação do teste
        assertNotNull(resultado);
        assertEquals("Ortopedia", resultado.getEspecialidade());

        verify(consultaRepository, times(1)).findById(1L);
        verify(consultaRepository, times(1)).save(consultaExistente);
    }

    @Test
    void deletarConsulta() {
        // Dados da consulta a ser deletado
        Consulta consulta = new Consulta();
        consulta.setEspecialidade("Ortopedia");

        // Configuração do mock
        when(consultaRepository.findById(1L)).thenReturn(Optional.of(consulta));

        // Execução do método
        consultaService.deletarConsulta(1L);

        // Validação do teste
        verify(consultaRepository, times(1)).findById(1L);
        verify(consultaRepository, times(1)).deleteById(1L);
    }
}
