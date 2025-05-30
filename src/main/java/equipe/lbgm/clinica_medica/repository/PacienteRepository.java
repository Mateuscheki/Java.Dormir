package equipe.lbgm.clinica_medica.repository;

import equipe.lbgm.clinica_medica.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}