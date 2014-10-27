package model.dividirEquipos;

import java.util.Collection;
import java.util.PriorityQueue;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import model.inscripcion.Inscripcion;

import org.uqbar.commons.utils.Transactional;

@Transactional
@Entity
@DiscriminatorValue("1,4,5,8,9")


public class CriterioParaDividir2 extends CriterioParaDividirEquipos {
	public void dividirEquipos(Collection<Inscripcion> equipo1, Collection<Inscripcion> equipo2, PriorityQueue<Inscripcion> primeros10Ordenados){
		equipo1.add(primeros10Ordenados.poll());
		equipo2.add(primeros10Ordenados.poll());
		equipo2.add(primeros10Ordenados.poll());
		equipo1.add(primeros10Ordenados.poll());
		equipo1.add(primeros10Ordenados.poll());
		equipo2.add(primeros10Ordenados.poll());
		equipo2.add(primeros10Ordenados.poll());
		equipo1.add(primeros10Ordenados.poll());
		equipo1.add(primeros10Ordenados.poll());
		equipo2.add(primeros10Ordenados.poll());
	}
	public String getNombre(){
		return "1,4,5,8,9";
		}
}
