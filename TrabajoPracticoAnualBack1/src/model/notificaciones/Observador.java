package model.notificaciones;

import model.futbol5.Jugador;
import model.futbol5.Partido;
/*@Entity
@Table(name = "Observadores")
@Inheritance(strategy = SINGLE_TABLE)
@DiscriminatorColumn(name = "Notificación")
*/
public abstract class Observador extends model.futbol5.PersistentEntity {
	public abstract void notificarNuevaInscripcion(Jugador jugador, Partido partido);
	public abstract void notificarReemplazoDeInscSinSustituto(Partido partido);
	public abstract void notificarPartidoConfirmado(Partido partido);
	public abstract void notificarPartidoDesconfirmado(Partido partido);
}

