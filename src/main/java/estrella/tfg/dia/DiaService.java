package estrella.tfg.dia;

import estrella.tfg.dia.model.Dia;
import estrella.tfg.dia.model.DiaDto;

import java.util.List;

public interface DiaService {

    Dia get(Long id);


    /**
     * Método para crear o actualizar un {@link Dia}
     *
     * @param id PK de la entidad
     * @param dto datos de la entidad
     */
    void save(Long id, DiaDto dto);

    /**
     * Método para crear o actualizar un {@link Dia}
     *
     * @param id PK de la entidad
     */
    void delete(Long id) throws Exception;

    /**
     * Recupera un listado de autores {@link Dia}
     *
     * @return {@link List} de {@link Dia}
     */
    List<Dia> findAll();

    DiaDto findById(Long id);
}

