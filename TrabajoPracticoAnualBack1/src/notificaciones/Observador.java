package notificaciones;

import futbol5.Jugador;
import futbol5.Partido;

public abstract class Observador {
	public abstract void notificarNuevaInscripcion(Jugador jugador, Partido partido);
	public abstract void notificarReemplazoDeInscSinSustituto(Partido partido);
	public abstract void notificarPartidoConfirmado(Partido partido);
	public abstract void notificarPartidoDesconfirmado(Partido partido);
}

