package model.notificaciones;

import static javax.persistence.InheritanceType.SINGLE_TABLE;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.Table;

import model.futbol5.Jugador;
import model.futbol5.Partido;
@Entity
@Table(name = "Observadores")
@Inheritance(strategy = SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo")
public abstract class Observador extends model.futbol5.PersistentEntity {
	public abstract void notificarNuevaInscripcion(Jugador jugador, Partido partido);
	public abstract void notificarReemplazoDeInscSinSustituto(Partido partido);
	public abstract void notificarPartidoConfirmado(Partido partido);
	public abstract void notificarPartidoDesconfirmado(Partido partido);
}

