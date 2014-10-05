package futbol5;
import inscripcion.Inscripcion;
import inscripcion.InscripcionCondicional;
import inscripcion.InscripcionEstandar;
import inscripcion.InscripcionSolidaria;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.PriorityQueue;

import notificaciones.Observador;
import dividirEquipos.CriterioParaDividirEquipos;
import ordenamiento.CriterioDeOrden;
import excepciones.Hay10EstandarException;
import excepciones.NoHay10InscriptosParaGenerarEquiposException;
import excepciones.EquiposConfirmadosException;

public class Partido {
	private LocalTime horario;
	private LocalDate fecha;
	private String lugar;
	private boolean equiposConfirmados;
	private Collection<Observador> observadores = new ArrayList<>();
	private PriorityQueue<Inscripcion> inscripciones=(new PriorityQueue<>(Comparator.
			comparing(inscripcion->inscripcion.getPrioridad())));
	private Collection<Inscripcion> equipo1 = new LinkedList<>();
	private Collection<Inscripcion> equipo2 = new LinkedList<>();
	private CriterioParaDividirEquipos criterioParaDividirEquipos;
	private CriterioDeOrden criterioDeOrden;
	
	public Partido(LocalDate dia, LocalTime hora,String lugar) {
		this.fecha= dia;
		this.horario= hora;
		this.lugar=lugar;
	}	
	
	public LocalTime getHorario() {
		return horario;
	}

	public void setHorario(LocalTime horario) {
		this.horario = horario;
	}
	
	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	
	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}
	
	public boolean getEquiposConfirmados() {
		return equiposConfirmados;
	}

	public void equiposConfirmados(){
    	equiposConfirmados= true;
    	for(Inscripcion inscripcion: equipo1){
    		inscripcion.getJugador().aumentarCantidadPartidosJugados();
    	}
    	for(Inscripcion inscripcion: equipo2){
    		inscripcion.getJugador().aumentarCantidadPartidosJugados();
    	}
    }	
	
	public Collection<Inscripcion> getEquipo1() {
		return equipo1;
	}

	public void agregarAEquipo1(Inscripcion insc) {
		this.equipo1.add(insc);
	}

	public Collection<Inscripcion> getEquipo2() {
		return equipo2;
	}

	public void agregarAEquipo2(Inscripcion insc) {
		this.equipo1.add(insc);
	}
	
	public Collection<Observador> getObservadores() {
		return observadores;
	}

	public void agregarObservador(Observador observador) {
		observadores.add(observador);
	}
	
	public CriterioDeOrden getCriterioDeOrden() {
		return criterioDeOrden;
	}

	public void setCriterioDeOrden(CriterioDeOrden criterio) {
		this.criterioDeOrden=criterio;
	}
	
	public CriterioParaDividirEquipos getCriterioParaDividirEquipos() {
		return criterioParaDividirEquipos;
	}

	public void setCriterioParaDividirEquipos(
			CriterioParaDividirEquipos criterioParaDividirEquipos) {
		this.criterioParaDividirEquipos = criterioParaDividirEquipos;
	}
	
	public  PriorityQueue<Inscripcion> getInscripciones(){
		return inscripciones;
	}
	
	public void altaInscripcion(Inscripcion inscripcion) {
		if(this.getEquiposConfirmados())
			throw new EquiposConfirmadosException("No se puede realizar la inscripcion porque los equipos ya fueron confirmados");
		if(cantidadInscriptosEstandar()>=10)
			throw new Hay10EstandarException("No se puede realizar la inscripcion porque ya hay 10 inscriptos en modo estandar para el partido");
		if(cantidadInscriptosEstandar()==9){
			for (Observador observador : observadores)  
				 observador.notificarPartidoConfirmado(this);
		}
			inscripciones.add(inscripcion);
			 for (Observador observador : observadores)  
				 observador.notificarNuevaInscripcion(inscripcion.jugador,this);
	}
	
	public void BajaInscripcion(Inscripcion inscripcionBaja, Inscripcion inscripcionAlta){
		if(this.getEquiposConfirmados())
			throw new EquiposConfirmadosException("No se puede desinscribir porque los equipos ya fueron confirmados");
		inscripciones.remove(inscripcionBaja);
		if(cantidadInscriptosEstandar()==9){
			for (Observador observador : observadores)  
				 observador.notificarPartidoDesconfirmado(this);
		}
		if(inscripcionAlta!= null)
			this.altaInscripcion(inscripcionAlta);
		else{
			inscripcionBaja.jugador.incrementarcantidadInfracPorNoTenerSustituto();
			inscripcionBaja.jugador.agregarInfraccion(this, "No tiene sustituto");
				for (Observador observador : observadores)  
				observador.notificarReemplazoDeInscSinSustituto(this);
		}
	}
	
	public int cantidadInscriptosEstandar() {
		return (int) inscripciones.stream().filter(inscripcion -> inscripcion instanceof InscripcionEstandar).count();
	}
	
	public int cantidadInscriptosCondicionales() {
		return (int) inscripciones.stream().filter(inscripcion -> inscripcion instanceof InscripcionCondicional).count();
	}
	
	public int cantidadInscriptosSolidarios() {
		return (int) inscripciones.stream().filter(inscripcion -> inscripcion instanceof InscripcionSolidaria).count();
	}
	
	public int cantidadTotalInscriptos(){
		return inscripciones.size();  
	}
	
	public PriorityQueue<Inscripcion> ordenarPrimeros10(){
		//aca utilizo inscripcionesAux pq no quiero modificar inscripciones, ya q el admin puede ordenar tantas veces como quiera
		//y no se debe recortar la colecc de inscripciones de manera definitiva
		LinkedList<Inscripcion> inscripcionesAux= new LinkedList<Inscripcion>();
		inscripcionesAux.addAll(inscripciones);
		inscripcionesAux.stream().limit(10);
		PriorityQueue<Inscripcion> primeros10Ordenados= new PriorityQueue<>(Comparator.comparing(inscripcion->
			this.getCriterioDeOrden().obtenerPromedio(inscripcion.getJugador()) ));		
		primeros10Ordenados.addAll(inscripcionesAux);
		return primeros10Ordenados;
	}
	
    public void generarEquipos(PriorityQueue<Inscripcion> primeros10Ordenados){
    	if(this.cantidadTotalInscriptos()<10)
    		throw new NoHay10InscriptosParaGenerarEquiposException("No se puede generarEquipos pq no hay 10 jugadores ");
    	criterioParaDividirEquipos.dividirEquipos(equipo1,equipo2,primeros10Ordenados);					
    }

}
	