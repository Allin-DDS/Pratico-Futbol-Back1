package model.inscripcion;

import javax.persistence.Entity;

//STUB
@Entity
public class Condicion extends model.futbol5.PersistentEntity{
	private String nombre;
	public boolean condicionDelJugador(){
		return true;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}




