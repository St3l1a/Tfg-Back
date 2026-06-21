package estrella.tfg.sentimiento;

import estrella.tfg.sentimiento.model.Sentimiento;
import org.springframework.data.repository.CrudRepository;

public interface SentimientoRepository extends CrudRepository<Sentimiento, Long> {
}
