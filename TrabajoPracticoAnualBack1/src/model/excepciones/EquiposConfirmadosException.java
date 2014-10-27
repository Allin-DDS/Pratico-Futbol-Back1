package model.excepciones;

@SuppressWarnings("serial")
public class EquiposConfirmadosException extends RuntimeException {
	public EquiposConfirmadosException(String mensaje){
		super(mensaje);
	}
}
