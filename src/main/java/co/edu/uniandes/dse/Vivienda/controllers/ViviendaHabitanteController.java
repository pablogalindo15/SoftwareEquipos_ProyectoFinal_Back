

package co.edu.uniandes.dse.Vivienda.controllers;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import co.edu.uniandes.dse.Vivienda.dto.*;
import co.edu.uniandes.dse.Vivienda.entities.HabitanteEntity;
import co.edu.uniandes.dse.Vivienda.exceptions.*;
import co.edu.uniandes.dse.Vivienda.services.ViviendaHabitanteService;

@RestController
@RequestMapping("/viviendas")
public class ViviendaHabitanteController {
    
     @Autowired
    private ViviendaHabitanteService viviendaHabitanteService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/{viviendaId}/habitantes/{habitanteId}")
    public HabitanteDetailDTO addHabitante(@PathVariable("viviendaId") Long viviendaId, @PathVariable("habitanteId") Long habitanteId) throws EntityNotFoundException {
        HabitanteEntity habitanteEntity = viviendaHabitanteService.addHabitante(viviendaId, habitanteId);
        return modelMapper.map(habitanteEntity, HabitanteDetailDTO.class);
    }
    
    @GetMapping("/{viviendaId}/habitantes/{habitanteId}")
    @ResponseStatus(code = HttpStatus.OK)
    public HabitanteDetailDTO getHabitante(@PathVariable("viviendaId") Long viviendaId, @PathVariable("habitanteId") Long habitanteId) throws EntityNotFoundException, IllegalOperationException {
            HabitanteEntity habitanteEntity= viviendaHabitanteService.getHabitante(viviendaId, habitanteId);
        return modelMapper.map(habitanteEntity, HabitanteDetailDTO.class);
    }

    @GetMapping("/{viviendaId}/habitantes")
    @ResponseStatus(code = HttpStatus.OK)
    public List<HabitanteEntity> getHabitantes(@PathVariable("viviendaId") Long viviendaId) throws EntityNotFoundException {
        List<HabitanteEntity> habitanteEntity = viviendaHabitanteService.getHabitantes(viviendaId);
        
        return modelMapper.map(habitanteEntity,  new TypeToken<List<HabitanteDetailDTO>>() {
		}.getType());
    }

    @PutMapping("/{viviendaId}/habitantes")
    public List<HabitanteDetailDTO> replaceHabitante(@PathVariable("viviendaId") Long viviendaId, @RequestBody List<HabitanteDTO> habitantes) throws EntityNotFoundException {

        List<HabitanteEntity> entities = modelMapper.map(habitantes, new TypeToken<List<HabitanteEntity>>() {
		}.getType());

        List<HabitanteEntity> habitantesList = viviendaHabitanteService.replaceHabitantes(viviendaId, entities);

        return modelMapper.map(habitantesList,  new TypeToken<List<HabitanteDetailDTO>>() {
		}.getType());
    }

    
    @DeleteMapping("/{viviendaId}/habitantes/{habitanteId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void removeHabitante(@PathVariable("viviendaId") Long viviendaId, @PathVariable("habitanteId") Long habitanteId) throws EntityNotFoundException{
        viviendaHabitanteService.removeHabitante(viviendaId, habitanteId);
    }


}
