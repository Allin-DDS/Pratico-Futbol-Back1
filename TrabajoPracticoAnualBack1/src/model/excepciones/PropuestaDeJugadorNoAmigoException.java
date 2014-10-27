package model.excepciones;

@SuppressWarnings("serial")
public class PropuestaDeJugadorNoAmigoException extends RuntimeException {
	public PropuestaDeJugadorNoAmigoException(String mensaje){
		super(mensaje);
	}
}
