package amarilla.cloud.lp4.reposotory;

import amarilla.cloud.lp4.model.AgenciaReguladora;
import amarilla.cloud.lp4.model.Esporte;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AgenciaReguladoraRepository extends JpaRepository<AgenciaReguladora, Long> {
    List<AgenciaReguladora> findByNomeContainingIgnoreCase (String nome);

}
