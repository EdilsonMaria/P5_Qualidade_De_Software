package br.com.edilsonmaria.medicalconsult.consulta.services;

import br.com.edilsonmaria.medicalconsult.consulta.domain.Consulta;
import br.com.edilsonmaria.medicalconsult.consulta.repository.ConsultaRepository;
import br.com.edilsonmaria.medicalconsult.exception.ExceptionDataIntegrityViolation;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    public Consulta cadastrarConsulta(Consulta consulta) {
        consulta.setIdConsulta(null);
        return consultaRepository.save(consulta);
    }

    public List<Consulta> listarConsultas() {
        return consultaRepository.findAll();
    }

    public Consulta buscarConsulta(Long id) {
        Optional<Consulta> consulta = consultaRepository.findById(id);
        return consulta.orElseThrow(
                () -> new ObjectNotFoundException("Especialidade não encontrada", id));
    }

    public void deletarConsulta(Long id) {
        buscarConsulta(id);
        try {
            consultaRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new ExceptionDataIntegrityViolation("Não é possivel excluir, porque há entidades de ralacionamento");
        }
    }

    public Consulta atualizarConsulta(Consulta consulta) {
        Consulta novaConsulta = buscarConsulta(consulta.getIdConsulta());
        updateData(novaConsulta, consulta);
        return consultaRepository.save(novaConsulta);
    }

    private void updateData(Consulta novaConsulta, Consulta consulta) {
        novaConsulta.setDataConsulta(consulta.getDataConsulta());
        novaConsulta.setProfissional(consulta.getProfissional());
        novaConsulta.setEspecialidade(consulta.getEspecialidade());
    }

}
