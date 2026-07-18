package estrella.tfg.share;


import estrella.tfg.registro.model.RegistroDto;
import estrella.tfg.sentimiento.model.Sentimiento;
import estrella.tfg.sentimiento.model.SentimientoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/shared")
@RestController
@CrossOrigin(origins = "*")
public class ShareController {
    @Autowired
    ShareService shareService;

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public List<Long> obtenerUsuariosCompartidos(@PathVariable("id") Long idProfesional) {
        return shareService.getUsersWhoSharedWith(idProfesional);
    }

    @RequestMapping(path = "/user/{id}", method = RequestMethod.GET)
    public List<Long> obtenerRelacionesPorUsuario(@PathVariable("id") Long idUsuario) {
        return shareService.obtenerRelacionesDeUsuario(idUsuario);
    }


    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public void save(@PathVariable(name = "id", required = false) Long id,@RequestParam("correoProfresional") String correoProfresional) {

        this.shareService.save(id, correoProfresional);
    }


    @RequestMapping(path = "", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete( @RequestParam("idUsuario") Long idUsuario,
                        @RequestParam("idProfesional") Long idProfesional) {

        boolean eliminado = shareService.eliminarCompartirDatos(idUsuario, idProfesional);
        if (eliminado) {
            return ResponseEntity.noContent().build(); // 204
        } else {
            return ResponseEntity.notFound().build(); // 404
        }
    }

}
