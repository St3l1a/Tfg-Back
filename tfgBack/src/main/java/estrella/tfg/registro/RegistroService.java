package estrella.tfg.registro;

import estrella.tfg.registro.model.Registro;
import estrella.tfg.registro.model.RegistroDto;

import java.util.List;

public interface RegistroService {

    Registro get(Long id);


    /**
     * Método para crear o actualizar un {@link Registro}
     *
     * @param id PK de la entidad
     * @param dto datos de la entidad
     */
    void save(Long id, RegistroDto dto);

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
    List<Registro> findAll();

    RegistroDto findById(Long id);
}

