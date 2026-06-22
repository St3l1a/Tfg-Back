package estrella.tfg.sentimiento;

import estrella.tfg.sentimiento.model.Sentimiento;
import estrella.tfg.sentimiento.model.SentimientoDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping(value = "/sentimiento")
@RestController
@CrossOrigin(origins = "*")
public class SentimientoController {
    @Autowired
    SentimientoService sentimientoService;

    @Autowired
    ModelMapper mapper;


    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<SentimientoDto> findAll() {
        List<Sentimiento> sentimientos = this.sentimientoService.findAll();

        return sentimientos.stream().map(e -> mapper.map(e, SentimientoDto.class)).collect(Collectors.toList());
    }


    @RequestMapping(path = { "", "/{id}" }, method = RequestMethod.PUT)
    public void save(@PathVariable(name = "id", required = false) Long id, @RequestBody SentimientoDto dto) {

        this.sentimientoService.save(id, dto);
    }

    /**
     * Método para borrar una {@link Sentimiento}
     *
     * @param id PK de la entidad
     */

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) throws Exception {

        this.sentimientoService.delete(id);
    }

}