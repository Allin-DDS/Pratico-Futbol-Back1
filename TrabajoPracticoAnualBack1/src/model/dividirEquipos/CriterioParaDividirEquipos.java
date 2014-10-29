package model.dividirEquipos;

import static javax.persistence.InheritanceType.SINGLE_TABLE;

import java.util.Collection;
import java.util.PriorityQueue;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.Table;

import model.inscripcion.Inscripcion;

import org.uqbar.commons.utils.TransactionalAndObservable;
@TransactionalAndObservable
@Entity
@Table(name = "Criterios_de_division")
@Inheritance(strategy = SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo")

public abstract class CriterioParaDividirEquipos extends model.futbol5.PersistentEntity {
	public abstract void dividirEquipos(Collection<Inscripcion> equipo1, Collection<Inscripcion> equipo2,
			PriorityQueue<Inscripcion> primeros10Ordenados);
	public abstract String getNombre();
	public void setNombre(String nombre) {
	}
}
