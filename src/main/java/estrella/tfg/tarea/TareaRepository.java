package estrella.tfg.tarea;

import estrella.tfg.tarea.model.Tarea;
import org.springframework.data.repository.CrudRepository;

public interface TareaRepository extends CrudRepository<Tarea, Long> {
}
