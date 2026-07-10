package estrella.tfg.registro;

import estrella.tfg.registro.RegistroService;
import estrella.tfg.registro.model.Registro;
import estrella.tfg.registro.model.RegistroDto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RequestMapping(value = "/registro")
@RestController
@CrossOrigin(origins = "*")
public class RegistroController {
    @Autowired
    RegistroService registroService;

    @Autowired
    ModelMapper mapper;


    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<RegistroDto> findAll() {
        List<Registro> registros = this.registroService.findAll();

        return registros.stream().map(e -> mapper.map(e, RegistroDto.class)).collect(Collectors.toList());
    }

    @RequestMapping(path = "/{id}" , method = RequestMethod.GET)
    public RegistroDto findById(@PathVariable(name = "id", required = true) Long id){
        return this.registroService.findById(id);
    }

    @RequestMapping(path = "/user/{id}" , method = RequestMethod.GET)
    public List<RegistroDto> findByUserId(@PathVariable(name = "id", required = true) Long id){
        return this.registroService.findByUserId(id);
    }


    @RequestMapping(path = { "", "/{id}" }, method = RequestMethod.PUT)
    public void save(@PathVariable(name = "id", required = false) Long id, @RequestBody RegistroDto dto) {

        this.registroService.save(id, dto);
    }

    /**
     * Método para borrar una {@link Registro}
     *
     * @param id PK de la entidad
     */

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) throws Exception {

        this.registroService.delete(id);
    }
}
