package inscripcion;

import org.uqbar.commons.model.Entity;
import org.uqbar.commons.utils.Observable;
import org.uqbar.commons.utils.Transactional;

import futbol5.Jugador;
@SuppressWarnings("serial")
@Transactional
@Observable

public class Inscripcion extends Entity{
	public Jugador jugador;
	int prioridad;
	public String getNombreJugador(){
		return jugador.getNombre();
		
	}
	
	public int getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(int prioridad) {
		this.prioridad = prioridad;
	}
	
	public Jugador getJugador() {
		return jugador;
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}
	
}	


