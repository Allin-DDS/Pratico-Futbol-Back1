package notificaciones;

import futbol5.Jugador;
import futbol5.Partido;

public class MailAAdministrador extends Observador {
	public void notificarReemplazoDeInscSinSustituto(Partido partido) {
		// TODO Auto-generated method stub
		//si dejaron de ser 10 (partido.cantidadtotalInscriptos()==9), aca se avisar� que el partido esta confiramdo al admin
	}
	
	public void notificarNuevaInscripcion(Jugador emisor, Partido partido) {
		// TODO Auto-generated method stub
		// m�todo vac�o
	}

	public void notificarPartidoConfirmado(Partido partido) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notificarPartidoDesconfirmado(Partido partido) {
		// TODO Auto-generated method stub
		
	}
}
