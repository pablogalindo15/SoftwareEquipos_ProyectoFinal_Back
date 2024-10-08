package co.edu.uniandes.dse.Vivienda.controllers;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.modelmapper.TypeToken;
import co.edu.uniandes.dse.Vivienda.dto.ServicioDTO;
import co.edu.uniandes.dse.Vivienda.dto.ServicioDetailDTO;
import co.edu.uniandes.dse.Vivienda.entities.ServicioEntity;
import co.edu.uniandes.dse.Vivienda.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.Vivienda.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.Vivienda.services.ServicioService;


@RestController
@RequestMapping("/servicios")
public class ServicioController {
    @Autowired
    private ServicioService servicioService;
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public ServicioDTO create(@RequestBody ServicioDTO servicioDTO) throws IllegalOperationException {
        ServicioEntity servicioEntity = servicioService.crearServicio(modelMapper.map(servicioDTO, ServicioEntity.class));
        return modelMapper.map(servicioEntity,ServicioDTO.class );
    }

    @GetMapping(value = "/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public ServicioDetailDTO findOne(@PathVariable("id") Long id) throws EntityNotFoundException {
	    ServicioEntity servicioEntity = servicioService.getServicio(id);
		return modelMapper.map(servicioEntity, ServicioDetailDTO.class);
	}

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<ServicioDetailDTO> findAll() {
        List<ServicioEntity> servicios = servicioService.getTodos();
        return modelMapper.map(servicios, new TypeToken<List<ServicioDetailDTO>>() {
        }.getType());
    }
    @PutMapping(value = "/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public ServicioDTO update(@PathVariable("id") Long id, @RequestBody ServicioDTO servicioDTO)
                        throws EntityNotFoundException, IllegalOperationException {
        ServicioEntity servicioEntity = servicioService.updateServicio(id, modelMapper.map(servicioDTO, ServicioEntity.class));
        return modelMapper.map(servicioEntity, ServicioDTO.class);
    }   
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) throws EntityNotFoundException, IllegalOperationException {
        servicioService.deleteServicio(id);
    }

}   
