package ordenamiento;

import java.util.stream.DoubleStream;
import java.util.stream.Stream;

import org.uqbar.commons.utils.TransactionalAndObservable;

import futbol5.Calificacion;
import futbol5.Jugador;
import futbol5.Partido;

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
