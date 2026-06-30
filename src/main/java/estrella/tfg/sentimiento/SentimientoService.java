package estrella.tfg.sentimiento;

import estrella.tfg.sentimiento.model.Sentimiento;
import estrella.tfg.sentimiento.model.SentimientoDto;

import java.util.List;

public interface SentimientoService {

    Sentimiento get(Long id);


    /**
     * Método para crear o actualizar un {@link Sentimiento}
     *
     * @param id PK de la entidad
     * @param dto datos de la entidad
     */
    void save(Long id, SentimientoDto dto);

    /**
     * Método para crear o actualizar un {@link Sentimiento}
     *
     * @param id PK de la entidad
     */
    void delete(Long id) throws Exception;

    /**
     * Recupera un listado de autores {@link Sentimiento}
     *
     * @return {@link List} de {@link Sentimiento}
     */
    List<Sentimiento> findAll();
    Sentimiento findById(Long id);
}

