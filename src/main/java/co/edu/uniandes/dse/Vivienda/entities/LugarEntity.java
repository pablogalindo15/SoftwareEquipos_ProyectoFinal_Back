package co.edu.uniandes.dse.Vivienda.entities;


import java.util.List; 
import java.util.ArrayList; 

import javax.persistence.Entity;


import javax.persistence.ManyToMany;

import lombok.Data;
import uk.co.jemos.podam.common.PodamExclude;
@Data
@Entity
public class LugarEntity extends BaseEntity {
    
    private String nombre;
    private String fotos;
    private Integer tiempoLlegada;
    private Boolean gratis;
    private Double precioMin;
    private Double precioMax;
    private Double coordenadaX;
    private Double coordenadaY;
    public enum tipoLugar {Universidad, Restaurante, Supermercado, Parque, CentroComercial };
    private tipoLugar tipo;

@PodamExclude
@ManyToMany (mappedBy = "lugarDeInteres_cercano")
private List<ViviendaEntity> viviendas_cercanas = new ArrayList<>();

}


