package model.notificaciones;

import model.futbol5.Jugador;
import model.futbol5.Partido;
//@Entity
//@DiscriminatorValue("MailAAmigos")

public class MailAAmigos extends Observador{

	public void notificarReemplazoDeInscSinSustituto(Partido partido) {
		// TODO Auto-generated method stub
		// m�todo vac�o
	}
	
	public void notificarNuevaInscripcion(Jugador emisor, Partido partido) {
		// TODO Auto-generated method stub
		//Aca avisara a los amigos del jugador inscripto 
	}

	public void notificarPartidoConfirmado(Partido partido) {
		// TODO Auto-generated method stub
		// m�todo vac�o
	}
	
	public void notificarPartidoDesconfirmado(Partido partido) {
		// TODO Auto-generated method stub
		// m�todo vac�o
	}
}
