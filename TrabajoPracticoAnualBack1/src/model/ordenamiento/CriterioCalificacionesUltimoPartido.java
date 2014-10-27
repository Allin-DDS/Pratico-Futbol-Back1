package model.ordenamiento;

import java.util.stream.DoubleStream;
import java.util.stream.Stream;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import model.futbol5.Calificacion;
import model.futbol5.Jugador;
import model.futbol5.Partido;
@Entity
@DiscriminatorValue("UltPartido")
public class CriterioCalificacionesUltimoPartido extends CriterioDeOrden {
	
	public String getNombre(){
		return "Promedio de notas del último partido";
		}
	

	
	public DoubleStream notas(Jugador jugador) {
		Partido ultimoPartido= jugador.obtenerUltimoPartido();
		return this.obtenerCalificacionesUltimoPartido(jugador,ultimoPartido).
					mapToDouble(calif -> calif.getNota());
		
	}
	
	public Stream<Calificacion> obtenerCalificacionesUltimoPartido(Jugador jugador, Partido ultimoPartido){
		return jugador.getCalificaciones().stream().filter(calificacion -> calificacion.esDelPartido(ultimoPartido));
	}



	@Override
	public void setPartidos(int ultimosPartidosSeleccionados) {
		// TODO Auto-generated method stub
		
	}

	
}
