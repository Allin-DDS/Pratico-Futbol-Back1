package ui.futbol5ViewModels;

import model.inscripcion.Inscripcion;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import org.uqbar.commons.model.UserException;
import org.uqbar.commons.utils.Observable;

import model.ordenamiento.CriterioCalificacionesUltimoPartido;
import model.ordenamiento.CriterioDeOrden;
import model.ordenamiento.CriterioHandicap;
import model.ordenamiento.MixDeCriterios;
import ui.entidadesUtiles.Repositorio;
import model.dividirEquipos.CriterioParaDividir2;
import model.dividirEquipos.CriterioParaDividirEquipos;
import model.dividirEquipos.CriterioParesEImpares;
import model.futbol5.Jugador;
import model.futbol5.Partido;

@Observable
public class GeneradorDeEquipoViewModel {
	

	private CriterioParaDividirEquipos criterioSeleccionado;
	private CriterioDeOrden ordenamientoSeleccionado;
	private int ultimosPartidosSeleccionados = 1;
	private List<CriterioParaDividirEquipos> criteriosDisponibles;
	private List<CriterioDeOrden> ordenamientosDisponibles;
	private Repositorio partidoYjugadores;
	private List<Inscripcion> equipoNro1;
	private List<Inscripcion> equipoNro2;
	private Inscripcion inscriptoSeleccionado;
	private Inscripcion inscripto;
		
	public void init() {
		
		List<CriterioParaDividirEquipos> criterios = new ArrayList<CriterioParaDividirEquipos>();
		criterios.add(new CriterioParesEImpares());
		criterios.add(new CriterioParaDividir2());
		
		MixDeCriterios criterioMixto = new MixDeCriterios();
		criterioMixto.agregarCriterioDeOrden(new CriterioHandicap());
		criterioMixto.agregarCriterioDeOrden(new CriterioCalificacionesUltimoPartido());
		
		List<CriterioDeOrden> ordenesGenerales = new ArrayList<CriterioDeOrden>();
		ordenesGenerales.add(new CriterioHandicap());
		ordenesGenerales.add(new CriterioCalificacionesUltimoPartido());
		ordenesGenerales.add(new model.ordenamiento.CriterioUltimasNCalificaciones(this.getUltimosPartidosSeleccionados()));
		ordenesGenerales.add(criterioMixto);
	
		this.setOrdenamientosDisponibles(ordenesGenerales);
		this.setCriteriosDisponibles(criterios);
		
		partidoYjugadores = new Repositorio();
		
		
	}
	public void generarEquiposTentativos() {
		
		this.validar();
		this.partidoYjugadores = new Repositorio();
		Partido partido = this.partidoYjugadores.getPartido();
		
			try{
			
		this.ordenamientoSeleccionado.setPartidos(this.getUltimosPartidosSeleccionados());
		partido.setCriterioDeOrden(this.ordenamientoSeleccionado);
		partido.setCriterioParaDividirEquipos(criterioSeleccionado);
		partido.generarEquipos(partido.ordenarPrimeros10());
		}
		catch(Exception e){
					throw new UserException("Error al generar los equipos");
			
		}

		setEquipoNro1((List<Inscripcion>) partido.getEquipo1());
		setEquipoNro2((List<Inscripcion>) partido.getEquipo2());
	}
	private void validar() {
		if (this.criterioSeleccionado == null) {
			throw new UserException("Debe elegir un criterio de división de equipo");
		}
		if (this.ordenamientoSeleccionado == null) {
			throw new UserException("Debe elegir un criterio de ordenamiento de equipo");
		}
		
	}
	public void confirmarEquipos() {
		if(this.equipoNro1 == null && this.equipoNro2 == null){
			throw new UserException("Primero debe generar los equipos tentantivos");
			}
		
		try{
			db.EntityManagerHelper.beginTransaction();

			this.partidoYjugadores.inicializarJugadores();
			this.partidoYjugadores.persistirJugadores();
			this.partidoYjugadores.persistirInscripciones();
			this.partidoYjugadores.partidosAnterioresPersistente(true);
			this.partidoYjugadores.persistirPartido(ordenamientoSeleccionado,criterioSeleccionado,ultimosPartidosSeleccionados);
		
	db.EntityManagerHelper.commit();
	}catch(Exception e){
			
		throw new UserException(e.toString());
	}
		//No se como mostrarlo con el formato que usa arena. 
		JOptionPane.showMessageDialog(null,"Los equipos se han confirmado satisfactoriamente");
	}
	
	
	//Getters and Setters
	public List<Inscripcion> getEquipoNro2() {
		return equipoNro2;
	}

	public void setEquipoNro2(List<Inscripcion> equipoNro2) {
		this.equipoNro2 = equipoNro2;
	}

	public Inscripcion getInscriptoSeleccionado() {
		return inscriptoSeleccionado;
	}

	public void setInscriptoSeleccionado(Inscripcion inscriptoSeleccionado) {
		this.inscriptoSeleccionado = inscriptoSeleccionado;
	}
	
	public List<CriterioDeOrden> getOrdenamientosDisponibles() {
		return ordenamientosDisponibles;
	}

	public void setOrdenamientosDisponibles(List<CriterioDeOrden> ordenamientosDisponibles) {
		this.ordenamientosDisponibles = ordenamientosDisponibles;
	}

	public CriterioParaDividirEquipos getCriterioSeleccionado() {
		return criterioSeleccionado;
	}

	public void setCriterioSeleccionado(CriterioParaDividirEquipos criterioSeleccionado) {
		this.criterioSeleccionado = criterioSeleccionado;
	}

	public List<CriterioParaDividirEquipos> getCriteriosDisponibles() {
		return criteriosDisponibles;
	}

	public void setCriteriosDisponibles(List<CriterioParaDividirEquipos> criteriosDisponibles) {
		this.criteriosDisponibles = criteriosDisponibles;
	}
	
	public int getUltimosPartidosSeleccionados() {
		return ultimosPartidosSeleccionados;
	}

	public void setUltimosPartidosSeleccionados(int ultimosPartidosSeleccionados) {
		this.ultimosPartidosSeleccionados = ultimosPartidosSeleccionados;
	}

	public CriterioDeOrden getOrdenamientoSeleccionado() {
		return ordenamientoSeleccionado;
	}

	public void setOrdenamientoSeleccionado(CriterioDeOrden ordenamientoSeleccionado) {
		this.ordenamientoSeleccionado = ordenamientoSeleccionado;
	}

	public List<Inscripcion> getEquipoNro1() {
		return equipoNro1;
	}

	public void setEquipoNro1(List<Inscripcion> equipoNro1) {
		this.equipoNro1 = equipoNro1;
	}

	public String getNombreJugador() {
		return this.inscriptoSeleccionado.getNombreJugador();
	}

	public Jugador getJugadorSeleccionado() {
		if (this.inscriptoSeleccionado == null) {
			throw new UserException("Debe seleccionar un jugador");
		}
		return this.inscriptoSeleccionado.getJugador();
	}

	public Inscripcion getInscripto() {
		return inscripto;
	}
	public void setInscripto(Inscripcion inscripto) {
		this.inscripto = inscripto;
	}
}

