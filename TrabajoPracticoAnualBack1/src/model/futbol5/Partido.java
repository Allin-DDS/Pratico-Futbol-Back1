package model.futbol5;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.PriorityQueue;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import model.dividirEquipos.CriterioParaDividirEquipos;
import model.excepciones.EquiposConfirmadosException;
import model.excepciones.Hay10EstandarException;
import model.excepciones.NoHay10InscriptosParaGenerarEquiposException;
import model.inscripcion.Inscripcion;
import model.inscripcion.InscripcionCondicional;
import model.inscripcion.InscripcionEstandar;
import model.inscripcion.InscripcionSolidaria;
import model.notificaciones.Observador;
import model.ordenamiento.CriterioDeOrden;
@Entity
@Table(name = "Partidos")
public class Partido extends PersistentEntity {
	@Transient
	private LocalTime horario;
	@Transient
	private LocalDate fecha;
	private String lugar;
	private boolean equiposConfirmados;
	@OneToMany
	private Collection<Observador> observadores = new ArrayList<>();
	@Transient
	private Collection<Inscripcion> inscripciones=(new PriorityQueue<>(Comparator.
			comparing(inscripcion->inscripcion.getPrioridad())));
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "partidos_inscripciones", joinColumns = { 
			@JoinColumn(name = "partido_Id") }, 
			inverseJoinColumns = { @JoinColumn(name = "inscripcion_Id")})
	private Collection<Inscripcion> inscriptos = new LinkedList<>();
	
	@Transient
	private Collection<Inscripcion> equipo1 = new LinkedList<>();
	@Transient
	private Collection<Inscripcion> equipo2 = new LinkedList<>();
	@OneToOne
	private CriterioParaDividirEquipos criterioParaDividirEquipos;
	@OneToOne
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
    	getEquipos();
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
		this.equipo2.add(insc);
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
	
	public  Collection<Inscripcion> getInscripciones(){
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
    public Collection<Inscripcion> getEquipos() {
		if(equipo1 != null){
			for(Inscripcion inscripcion: equipo1){
			inscripcion.setNumeroDeEquipo(1);
			inscriptos.add(inscripcion);
			}
		}
		if(equipo2 != null){	
			for(Inscripcion inscripcion: equipo2){
					inscripcion.setNumeroDeEquipo(2);
					inscriptos.add(inscripcion);
			}
			}
		return inscriptos;
	}

}
	