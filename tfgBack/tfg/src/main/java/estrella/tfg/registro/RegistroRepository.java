package estrella.tfg.registro;

import estrella.tfg.registro.model.Registro;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RegistroRepository extends CrudRepository<Registro, Long> {
}
