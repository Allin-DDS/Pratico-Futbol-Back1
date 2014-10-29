package model.inscripcion;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import model.futbol5.Jugador;
@Entity
@DiscriminatorValue("Inscripción Solidaria")
public class InscripcionSolidaria extends Inscripcion{
	private static int contadorDeInstacias=100;
	public InscripcionSolidaria(){}
	public InscripcionSolidaria(Jugador jugador){
		InscripcionSolidaria.setContadorDeInstacias(InscripcionSolidaria.getContadorDeInstacias() - 1);
		this.jugador=jugador;
		this.prioridad=getContadorDeInstacias();				
	}
	public String getNombreJugador(){
		return jugador.getNombre();
		
	}
	public static int getContadorDeInstacias() {
		return contadorDeInstacias;
	}

	public static void setContadorDeInstacias(int contadorDeInstacias) {
		InscripcionSolidaria.contadorDeInstacias = contadorDeInstacias;
	}
}



