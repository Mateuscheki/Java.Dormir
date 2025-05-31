package equipe.lbgm.clinica_medica.repository;

import equipe.lbgm.clinica_medica.model.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgendaRepository extends JpaRepository<Agenda, Long> {
}