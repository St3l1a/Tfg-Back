package estrella.tfg.share;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface ShareRepository  extends CrudRepository<Share, Long> {

    @Query(value = """
        SELECT c.id_usuario FROM compartir_datos c WHERE c.id_profesional = :idProfesional
        """, nativeQuery = true)
    List<Long> obtenerUsuariosCompartidos(@Param("idProfesional") Long idProfesional);


    @Query(value = """
        SELECT c.id_profesional FROM compartir_datos c WHERE c.id_usuario = :idUsuario
        """, nativeQuery = true)
    List<Long> obtenerRelacionesDeUsuario(@Param("idUsuario") Long idUsuario);



    @Modifying
    @Transactional
    @Query(value = "DELETE FROM compartir_datos WHERE id_usuario = :idUsuario AND id_profesional = :idProfesional", nativeQuery = true)
    void deleteByUsuarioAndProfesional(@Param("idUsuario") Long idUsuario,
                                       @Param("idProfesional") Long idProfesional);

    // Opcional: para saber si existe algún registro antes de borrar
    @Query(value = "SELECT COUNT(*) FROM compartir_datos WHERE id_usuario = :idUsuario AND id_profesional = :idProfesional", nativeQuery = true)
    long countByUsuarioAndProfesional(@Param("idUsuario") Long idUsuario,
                                      @Param("idProfesional") Long idProfesional);
}
