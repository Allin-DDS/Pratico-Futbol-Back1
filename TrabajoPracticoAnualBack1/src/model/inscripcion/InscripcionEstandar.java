package model.inscripcion;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import model.futbol5.Jugador;
@Entity
@DiscriminatorValue("Inscripción Estandar")
public class InscripcionEstandar extends Inscripcion {
	
	public InscripcionEstandar(){}

	public InscripcionEstandar(Jugador jugador){
		this.jugador=jugador;
		this.prioridad=1;
	}
	public String getNombreJugador(){
		return jugador.getNombre();
		
	}
	
}
