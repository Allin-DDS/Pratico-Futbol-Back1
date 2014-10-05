package notificaciones;

import futbol5.Jugador;
import futbol5.Partido;

public class MailAAmigos extends Observador{

	public void notificarReemplazoDeInscSinSustituto(Partido partido) {
		// TODO Auto-generated method stub
		// método vacío
	}
	
	public void notificarNuevaInscripcion(Jugador emisor, Partido partido) {
		// TODO Auto-generated method stub
		//Aca avisara a los amigos del jugador inscripto 
	}

	public void notificarPartidoConfirmado(Partido partido) {
		// TODO Auto-generated method stub
		// método vacío
	}
	
	public void notificarPartidoDesconfirmado(Partido partido) {
		// TODO Auto-generated method stub
		// método vacío
	}
}
