package model.futbol5;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Calificaciones")
public class Calificacion extends PersistentEntity{
	
	@ManyToOne
	private Jugador calificador;
	@ManyToOne
	private Partido partido;
	private String comentario;
	private int nota;
	
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
	public Jugador getCalificador() {
		return calificador;
	}

	public void setCalificador(Jugador calificador) {
		this.calificador = calificador;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
}
