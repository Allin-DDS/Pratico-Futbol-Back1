package model.inscripcion;

import static javax.persistence.InheritanceType.SINGLE_TABLE;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import model.futbol5.Jugador;

import org.uqbar.commons.utils.Observable;
import org.uqbar.commons.utils.Transactional;
@Entity
@Transactional
@Observable
@Table(name = "Inscripciones")
@Inheritance(strategy = SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo")
public class Inscripcion extends model.futbol5.PersistentEntity{

	@ManyToOne
    @JoinColumn(name="jugador_inscripcion")
	public Jugador jugador;

	int prioridad;
	int numeroDeEquipo = 0;
	@Transient
	int id_Jugador;
	public int getId_Jugador() {
		return id_Jugador;
	}

	public void setId_Jugador(int id_Jugador) {
		this.id_Jugador = id_Jugador;
	}

	public int getNumeroDeEquipo() {
		return numeroDeEquipo;
	}

	public Inscripcion(){}
	public void setNumeroDeEquipo(int numeroDeEquipo) {
		this.numeroDeEquipo = numeroDeEquipo;
	}

	public String getNombreJugador(){
		return jugador.getNombre();
		
	}
	
	public int getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(int prioridad) {
		this.prioridad = prioridad;
	}
	
	public Jugador getJugador() {
		return jugador;
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}
	
}	


