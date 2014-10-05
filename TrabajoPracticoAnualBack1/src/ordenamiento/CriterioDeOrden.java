package ordenamiento;

import java.util.stream.DoubleStream;

import org.uqbar.commons.model.Entity;
import org.uqbar.commons.utils.TransactionalAndObservable;

import futbol5.Jugador;
@SuppressWarnings("serial")
@TransactionalAndObservable
public abstract class CriterioDeOrden extends Entity {
	
	public double obtenerPromedio(Jugador jugador){
		return this.notas(jugador).average().getAsDouble();
	}
	public abstract String getNombre();
	public abstract DoubleStream notas(Jugador jugador);
	public abstract void setPartidos(int ultimosPartidosSeleccionados);
}
