package dividirEquipos;

import inscripcion.Inscripcion;

import java.util.Collection;
import java.util.PriorityQueue;

import org.uqbar.commons.model.Entity;
import org.uqbar.commons.utils.TransactionalAndObservable;
@SuppressWarnings("serial")
@TransactionalAndObservable
public abstract class CriterioParaDividirEquipos extends Entity {

	public abstract void dividirEquipos(Collection<Inscripcion> equipo1, Collection<Inscripcion> equipo2,
			PriorityQueue<Inscripcion> primeros10Ordenados);
	public abstract String getNombre();
}
