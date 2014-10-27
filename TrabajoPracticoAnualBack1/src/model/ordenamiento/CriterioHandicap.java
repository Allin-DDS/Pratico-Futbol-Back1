package model.ordenamiento;

import java.util.stream.DoubleStream;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import model.futbol5.Jugador;

@Entity
@DiscriminatorValue("Handicap")
public class CriterioHandicap extends CriterioDeOrden {

	public DoubleStream notas(Jugador jugador) {
		return DoubleStream.builder().add(jugador.getHandicap()).build();
	}
	public String getNombre(){
		return "Hándicap";
	}
	@Override
	public void setPartidos(int ultimosPartidosSeleccionados) {
		// TODO Auto-generated method stub
		
	}
}
