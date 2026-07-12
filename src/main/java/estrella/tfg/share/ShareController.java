package estrella.tfg.share;


import estrella.tfg.registro.model.RegistroDto;
import org.springframework.beans.factory.annotation.Autowired;
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
}
