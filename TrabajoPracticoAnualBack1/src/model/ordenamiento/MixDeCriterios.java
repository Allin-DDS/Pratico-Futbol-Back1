package model.ordenamiento;

import java.util.Collection;
import java.util.ArrayList;
import java.util.stream.DoubleStream;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Embedded;
import javax.persistence.Entity;

import model.futbol5.Jugador;

@Entity
@DiscriminatorValue("Criterio Mix")
public class MixDeCriterios extends CriterioDeOrden{


	//Con Composite:
	@Embedded
	private Collection<CriterioDeOrden> criteriosDeOrden= new ArrayList<>();
	
	public Collection<CriterioDeOrden> getCriteriosDeOrden() {
		return criteriosDeOrden;
	}
	public String getNombre(){
		return "Mixto";
		}
	public void agregarCriterioDeOrden(CriterioDeOrden criteriosDeOrden) {
		this.criteriosDeOrden.add(criteriosDeOrden);
	}

	public DoubleStream notas(Jugador jugador) {
		return criteriosDeOrden.stream().mapToDouble(criterio -> criterio.obtenerPromedio(jugador));
	}
	@Override
	public void setPartidos(int ultimosPartidosSeleccionados) {
		// TODO Auto-generated method stub
		
	}

}
