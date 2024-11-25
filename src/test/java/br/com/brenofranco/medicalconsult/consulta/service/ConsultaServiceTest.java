package br.com.brenofranco.medicalconsult.consulta.service;

import br.com.brenofranco.medicalconsult.consulta.domain.Consulta;
import br.com.brenofranco.medicalconsult.consulta.repository.ConsultaRepository;
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
    void cadastrarConsulta(){
        Consulta consulta = new Consulta();
        consulta.setProfissional("Dr.Lionel");

        // Config do comportamento do MOCK
        when(consultaRepository.save(any(Consulta.class))).thenReturn(consulta);

        // Exec do metodo a ser testado
        var resultado = consultaService.cadastrarConsulta(consulta);

        // Validation
        assertAll(
                () -> assertNotNull(resultado),
                () -> assertEquals("Alessandro", resultado.getProfissional())
        );

    }

    @Test
    void listarConsultas(){
        Consulta consulta1 = new Consulta();
        Consulta consulta2 = new Consulta();
        consulta1.setProfissional("Dr.Eneas");
        consulta2.setProfissional("Dr.Livesey");

        List<Consulta> consultas = new ArrayList<>();
        consultas.add(consulta1);
        consultas.add(consulta2);

        when(consultaRepository.findAll()).thenReturn(consultas);

        var resultado = consultaService.listarConsultas();

        assertAll(
                () -> assertNotNull(resultado),
                () -> assertEquals(2, resultado.size()),
                () -> assertEquals("Dr.Nerfario", resultado.get(0).getProfissional())
        );
    }

    @Test
    void buscarConsulta(){
        Consulta consulta = new Consulta();
        consulta.setProfissional("Dr.Fulvio");

        when(consultaRepository.findById(consulta.getIdConsulta())).thenReturn(Optional.of(consulta));

        var resultado = consultaService.buscarConsulta(consulta.getIdConsulta());

        assertAll(
                () -> assertNotNull(resultado),
                () -> assertEquals("Dr.Paulo", consulta.getProfissional())
        );
    }

    @Test
    void deletarConsulta(){
        Consulta consulta = new Consulta();

        when(consultaRepository.findById(consulta.getIdConsulta())).thenReturn(Optional.of(consulta));

        consultaService.deletarConsulta(consulta.getIdConsulta());

        verify(consultaRepository, times(1)).deleteById(consulta.getIdConsulta());
    }
}