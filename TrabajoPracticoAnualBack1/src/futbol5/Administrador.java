package futbol5;
import inscripcion.Inscripcion;
import inscripcion.InscripcionPropuesta;
import inscripcion.InscripcionRechazada;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

public class Administrador {

	static String motivoRechazo;
	static Collection<Partido> partidos = new ArrayList<>();
	//static Collection<Jugador> jugadores = new ArrayList<>();
	static Collection<InscripcionPropuesta> inscripcionesPropuestas = new ArrayList<>();
	static Collection<InscripcionRechazada> inscripcionesRechazadas= new LinkedList<>();
    static {
    	//Inicializacion del singleton
    }

    public Administrador() { }

    public Collection<InscripcionPropuesta> getInscripcionesPropuestas() {
		return inscripcionesPropuestas;
	}

	public static void agregarInscripcionPropuesta(Inscripcion inscripcion, Partido partido) {
		InscripcionPropuesta inscPropuesta= new InscripcionPropuesta(inscripcion, partido);
		inscripcionesPropuestas.add(inscPropuesta);
	}
    
	public static Collection<Partido> getPartidos() {
		return partidos;
	}

	public static void agregarPartido(Partido partido) {
		partidos.add(partido);
	}

	/*
	public static Collection<Jugador> getJugadores() {
		return jugadores;
	}

	public static void agregarJugador(Jugador jugador) {
		jugadores.add(jugador);
	}
	*/
	
	public static Collection<InscripcionRechazada> getInscripcionesRechazadas() {
		return inscripcionesRechazadas;
	}

	public static void agregarAInscripcionesRechazadas(InscripcionRechazada inscripcionRechazada){
		inscripcionesRechazadas.add(inscripcionRechazada);
	}
	
	public static void aceptarInscripcionPropuesta(InscripcionPropuesta inscripcionPropuesta){
		inscripcionPropuesta.getPartido().altaInscripcion(inscripcionPropuesta.getInscripcion());
		inscripcionesPropuestas.remove(inscripcionPropuesta);
	}
		
	public static void rechazarInscripcionPropuesta(Inscripcion inscripcion){
		inscripcionesPropuestas.remove(inscripcion);
		InscripcionRechazada inscRechazada= new InscripcionRechazada(motivoRechazo,LocalDate.now(),inscripcion);
		agregarAInscripcionesRechazadas(inscRechazada);
	}
	
}
 