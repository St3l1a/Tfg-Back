package estrella.tfg.share;

import java.util.List;

public interface ShareService {
    List<Long> getUsersWhoSharedWith(Long id);
     List<Long> obtenerRelacionesDeUsuario(Long id);
     void save(Long id, String data);
     boolean eliminarCompartirDatos(Long idUsuario, Long idProfesional) ;

}
