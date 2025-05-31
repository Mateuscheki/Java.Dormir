package equipe.lbgm.clinica_medica.service;


import equipe.lbgm.clinica_medica.model.Agenda;
import equipe.lbgm.clinica_medica.repository.AgendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AgendaService {

    @Autowired
    private AgendaRepository agendaRepository;

    public List<Agenda> listarTodos() {
        return agendaRepository.findAll();
    }

    public Optional<Agenda> buscarPorId(Long id) {
        return agendaRepository.findById(id);
    }

    public Agenda salvar(Agenda agenda) {
        return agendaRepository.save(agenda);
    }

    public void deletar(Long id) {
        agendaRepository.deleteById(id);
    }
}