package estrella.tfg.usuario;

import estrella.tfg.usuario.model.Usuario;
import estrella.tfg.usuario.model.UsuarioDto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RequestMapping(value = "/usuario")
@RestController
@CrossOrigin(origins = "*")
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;

    @Autowired
    ModelMapper mapper;


    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<UsuarioDto> findAll() {
        List<Usuario> usuarios = this.usuarioService.findAll();

        return usuarios.stream().map(e -> mapper.map(e, UsuarioDto.class)).collect(Collectors.toList());
    }



    @RequestMapping(path = { "", "/{id}" }, method = RequestMethod.PUT)
    public void save(@PathVariable(name = "id", required = false) Long id, @RequestBody UsuarioDto dto) {

        this.usuarioService.save(id, dto);
    }

    /**
     * Método para borrar una {@link Usuario}
     *
     * @param id PK de la entidad
     */

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) throws Exception {

        this.usuarioService.delete(id);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public UsuarioDto findById(@PathVariable("id") Long id) {

        Usuario usuario = this.usuarioService.findById(id);

        return mapper.map(usuario, UsuarioDto.class);
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public Long login(  @RequestBody UsuarioDto dto ) {

        Long usuario = this.usuarioService.login( dto.getCorreo(), dto.getContrasena());

        if (usuario == null) {
            new RuntimeException("Usuario no encontrado");
        }

        return usuario;
    }

}