package amarilla.cloud.lp4.reposotory;

import amarilla.cloud.lp4.model.Esporte;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EsporteRepository extends JpaRepository<Esporte, Long> {
    List<Esporte> findByNomeContainingIgnoreCase (String nome);
}
