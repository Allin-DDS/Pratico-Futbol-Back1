package futbol5;


public class Calificacion{
	
	Jugador calificador;
	Partido partido;
	String comentario;
	int nota;
	
	public Calificacion(Jugador calificador, Partido partido,  String comentario, int nota) {
	this.calificador= calificador;
	this.partido=partido;
	this.comentario= comentario;
	this.nota= nota;
	}

	public int getNota() {
		return nota;
	}

	public void setNota(int nota) {
		this.nota = nota;
	}

	public Partido getPartido() {
		return partido;
	}

	public void setPartido(Partido partido) {
		this.partido = partido;
	}
	
	public boolean esDelPartido(Partido partido){
		return getPartido().equals(partido);
	}
}
