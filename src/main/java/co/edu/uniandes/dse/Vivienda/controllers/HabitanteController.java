package co.edu.uniandes.dse.Vivienda.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.HttpStatus;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import co.edu.uniandes.dse.Vivienda.dto.HabitanteDTO;
import co.edu.uniandes.dse.Vivienda.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.Vivienda.services.HabitanteService;
import co.edu.uniandes.dse.Vivienda.entities.HabitanteEntity;
import co.edu.uniandes.dse.Vivienda.exceptions.EntityNotFoundException;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.stream.Collectors;
import java.util.Collection;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import java.util.ArrayList;
import org.springframework.data.domain.Page;





@RestController
@RequestMapping("/habitantes")


public class HabitanteController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private HabitanteService habitanteService;



@PostMapping
@ResponseStatus(code = HttpStatus.CREATED)
public HabitanteDTO create (@RequestBody HabitanteDTO habitanteDTO) throws EntityNotFoundException, IllegalOperationException {
    HabitanteEntity habitanteEntity = habitanteService.createHabitante(modelMapper.map(habitanteDTO, HabitanteEntity.class));
    return modelMapper.map(habitanteEntity, HabitanteDTO.class);
}


@GetMapping(value = "/{id}")
@ResponseStatus(code = HttpStatus.OK)
public HabitanteDTO findOne(@PathVariable("id") Long id) throws EntityNotFoundException {
    HabitanteEntity habitanteEntity = habitanteService.getHabitante(id);
    return modelMapper.map(habitanteEntity, HabitanteDTO.class);
}

@GetMapping
@ResponseStatus(code = HttpStatus.OK)
public Collection<HabitanteDTO> findAll() {
    Collection<HabitanteEntity> habitanteEntities = habitanteService.getHabitantes();
    return habitanteEntities.stream().map(habitanteEntity -> modelMapper.map(habitanteEntity, HabitanteDTO.class)).collect(Collectors.toList());
}


@PutMapping(value = "/{id}")
@ResponseStatus(code = HttpStatus.OK)
public HabitanteDTO update(@PathVariable("id") Long id, @RequestBody HabitanteDTO habitanteDTO) throws EntityNotFoundException, IllegalOperationException {
    HabitanteEntity habitanteEntity = habitanteService.updateHabitante(id, modelMapper.map(habitanteDTO, HabitanteEntity.class));
    return modelMapper.map(habitanteEntity, HabitanteDTO.class);
}

@DeleteMapping(value = "/{id}")
@ResponseStatus(code = HttpStatus.NO_CONTENT)
public void delete(@PathVariable("id") Long id) throws EntityNotFoundException, IllegalOperationException{
    habitanteService.deleteHabitante(id);

}

@GetMapping("/sort/{fields}")
public List<HabitanteEntity > sortHabitantes(@PathVariable String fields) {

    return habitanteService.sortBasedUponSomeField(fields);
}

@GetMapping("/pagination/{offset}/{pageSize}")
public Page<HabitanteEntity> getHabitanteWithPagination(@PathVariable int offset, @PathVariable int pageSize) {
    return habitanteService.getHabitanteWithPagination(offset, pageSize);
}

@GetMapping("/paginationandsorting/{offset}/{pageSize}/{fields}")
public Page<HabitanteEntity> paginationAndSorting(@PathVariable int offset, @PathVariable int pageSize, @PathVariable String fields) {
    return habitanteService.getHabitanteWithPaginationAndSorting(offset, pageSize, fields);
}

}