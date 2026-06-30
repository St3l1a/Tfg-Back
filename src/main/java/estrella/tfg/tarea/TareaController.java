package estrella.tfg.tarea;

import estrella.tfg.tarea.model.Tarea;
import estrella.tfg.tarea.model.TareaDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RequestMapping(value = "/tarea")
@RestController
@CrossOrigin(origins = "*")
public class TareaController {
    @Autowired
    TareaService tareaService;

    @Autowired
    ModelMapper mapper;


    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<TareaDto> findAll() {
        List<Tarea> tareas = this.tareaService.findAll();

        return tareas.stream().map(e -> mapper.map(e, TareaDto.class)).collect(Collectors.toList());
    }

    @RequestMapping(path = "/{id}" , method = RequestMethod.GET)
    public TareaDto findById(@PathVariable(name = "id", required = true) Long id){
        return this.tareaService.findById(id);
    }


    @RequestMapping(path = { "", "/{id}" }, method = RequestMethod.PUT)
    public void save(@PathVariable(name = "id", required = false) Long id, @RequestBody TareaDto dto) {

        this.tareaService.save(id, dto);
    }

    /**
     * Método para borrar una {@link Tarea}
     *
     * @param id PK de la entidad
     */

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) throws Exception {

        this.tareaService.delete(id);
    }
}
