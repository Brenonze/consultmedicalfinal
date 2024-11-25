package br.com.brenofranco.medicalconsult.consulta.service;

import br.com.brenofranco.medicalconsult.consulta.domain.Consulta;
import br.com.brenofranco.medicalconsult.consulta.repository.ConsultaRepository;
import br.com.brenofranco.medicalconsult.exception.ExceptionDataIntegrityViolation;
import lombok.RequiredArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConsultaService {
    private final ConsultaRepository consultaRepository;

    public Consulta cadastrarConsulta(Consulta consulta){
        consulta.setIdConsulta(null);
        return consultaRepository.save(consulta);
    }

    public List<Consulta> listarConsultas(){
        return consultaRepository.findAll();
    }

    public Consulta buscarConsulta(Long id){
        Optional<Consulta> consulta = consultaRepository.findById(id);
        return consulta.orElseThrow(
                () -> new ObjectNotFoundException("Especialidade não encontrada! ID: ", id));
    }

    public void deletarConsulta(Long id){
        buscarConsulta(id);
        try {
            consultaRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new ExceptionDataIntegrityViolation("Não é possível excluir, porque há entidades relacionadas.");
        }
    }

    public Consulta atualizarConsulta(Consulta consulta){
        Consulta novaConsulta = buscarConsulta(consulta.getIdConsulta());
        updateData(novaConsulta, consulta);
        return consultaRepository.save(novaConsulta);
    }

    private void updateData(Consulta novaConsulta, Consulta consulta){
        novaConsulta.setDataConsulta(consulta.getDataConsulta());
        novaConsulta.setProfissional(consulta.getProfissional());
        novaConsulta.setEspecialidade(consulta.getEspecialidade());
    }
}
