package estrella.tfg.registro;

import estrella.tfg.registro.model.Registro;
import estrella.tfg.registro.model.RegistroDto;

import java.util.List;

public interface RegistroService {

    RegistroDto get(Long id);


    /**
     * Método para crear o actualizar un {@link Registro}
     *
     * @param id PK de la entidad
     * @param dto datos de la entidad
     */
    RegistroDto save(Long id, RegistroDto dto);

    /**
     * Método para crear o actualizar un {@link Registro}
     *
     * @param id PK de la entidad
     */
    void delete(Long id) throws Exception;

    /**
     * Recupera un listado de autores {@link Registro}
     *
     * @return {@link List} de {@link Registro}
     */
    List<RegistroDto> findAll();

    RegistroDto findById(Long id);

    List<RegistroDto> findByUserId(Long id);
}

