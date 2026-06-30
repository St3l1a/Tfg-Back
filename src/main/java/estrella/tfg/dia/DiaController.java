package estrella.tfg.dia;

import estrella.tfg.dia.model.Dia;
import estrella.tfg.dia.model.DiaDto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RequestMapping(value = "/dia")
@RestController
@CrossOrigin(origins = "*")
public class DiaController {
    @Autowired
    DiaService diaService;

    @Autowired
    ModelMapper mapper;


    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<DiaDto> findAll() {
        List<Dia> dias = this.diaService.findAll();

        return dias.stream().map(e -> mapper.map(e, DiaDto.class)).collect(Collectors.toList());
    }

    @RequestMapping(path = "/{id}" , method = RequestMethod.GET)
    public DiaDto findById(@PathVariable(name = "id", required = true) Long id){
        return this.diaService.findById(id);
    }


    @RequestMapping(path = { "", "/{id}" }, method = RequestMethod.PUT)
    public void save(@PathVariable(name = "id", required = false) Long id, @RequestBody DiaDto dto) {

        this.diaService.save(id, dto);
    }

    /**
     * Método para borrar una {@link Dia}
     *
     * @param id PK de la entidad
     */

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) throws Exception {

        this.diaService.delete(id);
    }
}
