package inscripcion;

import futbol5.Jugador;

public class InscripcionEstandar extends Inscripcion {
	
	public InscripcionEstandar(Jugador jugador){
		this.jugador=jugador;
		this.prioridad=1;
	}
	public String getNombreJugador(){
		return jugador.getNombre();
		
	}
	
}
