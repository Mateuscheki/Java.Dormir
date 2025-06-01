package equipe.lbgm.clinica_medica.repository;

import equipe.lbgm.clinica_medica.model.Agenda;
import equipe.lbgm.clinica_medica.model.Medico;
import equipe.lbgm.clinica_medica.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgendaRepository extends JpaRepository<Agenda, Long> {
    boolean existsByPaciente(Paciente paciente);
    boolean existsByMedico(Medico medico);
}