package model.inscripcion;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import model.futbol5.Jugador;
@Entity
@DiscriminatorValue("Condicional")
public class InscripcionCondicional extends Inscripcion {
	@OneToOne
	private Condicion condicion;

	public InscripcionCondicional(){}
	public InscripcionCondicional(Jugador jugador,Condicion condicion){
		this.jugador=jugador;
		this.condicion= condicion;
		this.prioridad=2;
	}
	public String getNombreJugador(){
		return jugador.getNombre();
		
	}
	public Condicion getCondicion() {
		return condicion;
	}

	public void setCondicion(Condicion condicion) {
		this.condicion = condicion;
	}
	
}
