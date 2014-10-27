package model.inscripcion;
import java.time.LocalDate;


public class InscripcionRechazada {
	private String motivo;
	private LocalDate fecha;
	private Inscripcion inscripcion;
	
	public InscripcionRechazada(String motivo,LocalDate fecha,Inscripcion inscripcion){
		this.motivo=motivo;
		this.fecha=fecha;
		this.inscripcion=inscripcion;
	}
	
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public Inscripcion getInscripcion() {
		return inscripcion;
	}

	public void setInscripcion(Inscripcion inscripcion) {
		this.inscripcion = inscripcion;
	}
}
