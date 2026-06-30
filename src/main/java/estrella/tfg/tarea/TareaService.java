package estrella.tfg.tarea;

import estrella.tfg.tarea.model.Tarea;
import estrella.tfg.tarea.model.TareaDto;

import java.util.List;

public interface TareaService {

    Tarea get(Long id);


    /**
     * Método para crear o actualizar un {@link Tarea}
     *
     * @param id PK de la entidad
     * @param dto datos de la entidad
     */
    void save(Long id, TareaDto dto);

    /**
     * Método para crear o actualizar un {@link Tarea}
     *
     * @param id PK de la entidad
     */
    void delete(Long id) throws Exception;

    /**
     * Recupera un listado de autores {@link Tarea}
     *
     * @return {@link List} de {@link Tarea}
     */
    List<Tarea> findAll();

    TareaDto findById(Long id);
}

