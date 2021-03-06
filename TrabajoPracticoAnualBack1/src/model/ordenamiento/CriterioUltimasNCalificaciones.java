package model.ordenamiento;

import java.util.stream.DoubleStream;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import model.futbol5.Jugador;
@Entity
@DiscriminatorValue("Criterio N Calif.")
public class CriterioUltimasNCalificaciones extends CriterioDeOrden {
	private int cantidadCalificaciones;
	
	public String getNombre(){
		return "Promedio de notas de los �ltimos n partidos";
		}
	public CriterioUltimasNCalificaciones(int cantCalific){
		
		this.cantidadCalificaciones=cantCalific;
	}
	
	public int getCantidadCalificaciones() {
		return cantidadCalificaciones;
	}
	
	public void setCantidadCalificaciones(int cantidadCalificaciones) {
		this.cantidadCalificaciones = cantidadCalificaciones;
	}
	
	public DoubleStream notas(Jugador jugador) {
		return this.ultimosNCalificaciones(this.obtenerNotas(jugador));
	}
	
	private DoubleStream obtenerNotas(Jugador jugador) {
		return jugador.getCalificaciones().stream().mapToDouble(calificacion -> calificacion.getNota());
	}
	
	private DoubleStream ultimosNCalificaciones(DoubleStream doubleStream) {
		//son los primeros N pq las calificaciones estan ordenadas de la fecha m�s reciente a la m�s vieja
		return doubleStream.limit(cantidadCalificaciones);
	}
	@Override
	public void setPartidos(int ultimosPartidosSeleccionados) {
		this.cantidadCalificaciones = ultimosPartidosSeleccionados;
		
	}
	
}