package ordenamiento;

import futbol5.Jugador;

import java.util.Collection;
import java.util.ArrayList;
import java.util.stream.DoubleStream;

import org.uqbar.commons.utils.TransactionalAndObservable;

public class MixDeCriterios extends CriterioDeOrden{


	//Con Composite:
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
