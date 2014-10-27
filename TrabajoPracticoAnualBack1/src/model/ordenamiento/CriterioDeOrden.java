package model.ordenamiento;

import static javax.persistence.InheritanceType.SINGLE_TABLE;

import java.util.stream.DoubleStream;
import javax.persistence.Entity;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Inheritance;
import javax.persistence.Table;

import model.futbol5.Jugador;

import org.uqbar.commons.utils.TransactionalAndObservable;
@TransactionalAndObservable

@Entity
@Table(name = "Criterios_de_orden")
@Inheritance(strategy = SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo")

public abstract class CriterioDeOrden extends model.futbol5.PersistentEntity{
	
	public double obtenerPromedio(Jugador jugador){
		return this.notas(jugador).average().getAsDouble();
	}
	public abstract String getNombre();
	public abstract DoubleStream notas(Jugador jugador);
	public abstract void setPartidos(int ultimosPartidosSeleccionados);
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
	}
}
