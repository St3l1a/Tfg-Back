package estrella.tfg.share;

import estrella.tfg.registro.model.Registro;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface ShareRepository  extends CrudRepository<Share, Long> {

    @Query(value = """
    SELECT c.id_usuario FROM compartir_datos c WHERE c.id_profesional = :idProfesional
        """, nativeQuery = true)
    List<Long> obtenerUsuariosCompartidos(@Param("idProfesional") Long idProfesional);

}
