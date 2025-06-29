package equipe.lbgm.clinica_medica.repository;

import equipe.lbgm.clinica_medica.model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {
}