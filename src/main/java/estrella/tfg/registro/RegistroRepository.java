package estrella.tfg.registro;

import estrella.tfg.registro.model.Registro;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RegistroRepository extends CrudRepository<Registro, Long> {
    @Query(nativeQuery = true, value = """
        SELECT r.*
        FROM registro r
        JOIN dia d ON r.id_dia = d.id
        WHERE d.id_usuario = :id;
""")
    List<Registro> findRegistroByUserId(Long id);

}
