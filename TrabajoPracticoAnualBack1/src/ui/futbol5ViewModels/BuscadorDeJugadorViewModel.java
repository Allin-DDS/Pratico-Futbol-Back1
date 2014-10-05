package ui.futbol5ViewModels;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.uqbar.commons.model.UserException;
import org.uqbar.commons.utils.Observable;

import ui.entidadesUtiles.ComboBoxBoolean;
import ui.entidadesUtiles.ComboBoxSerial;
import ui.entidadesUtiles.Repositorio;
import futbol5.Jugador;
@Observable
public class BuscadorDeJugadorViewModel{

private List<Jugador> resultadoDeBusqueda;
private Jugador jugadorSeleccionado;
private String nombre;
private String apodo;
private String fecha = null;
private int handicap;
private double promedio;
private ComboBoxBoolean handicapElegidoCriterio;
private ComboBoxSerial infraccionElegidaCriterio;
private ComboBoxBoolean promedioElegidoCriterio;
private List<ComboBoxSerial> filtrosDeInfraccion;



	public void buscar() {
		
		this.validar();
		Jugador jugador = new Jugador(0);
		jugador.setApodo(apodo);
		jugador.setFechaDeNacimiento(this.fechaRango());
		jugador.setHandicap(handicap);
		jugador.setNombre(nombre);
		jugador.setPromedioBuscado(promedio);
		jugador.setHandicapCriterio(handicapElegidoCriterio.isBooleano());
		jugador.setInfraccionesCriterio(infraccionElegidaCriterio.getSerial());
		jugador.setPromedioCriterio(promedioElegidoCriterio.isBooleano());
		
		resultadoDeBusqueda = Repositorio.getInstance().buscar(jugador);
	}


	private void validar() {
		if (this.infraccionElegidaCriterio == null) {
			throw new UserException("Debe elegir un filtrado de infracción");
		}
		if (this.handicapElegidoCriterio == null) {
			throw new UserException("Debe elegir un filtrado de hándicap");
		}
		if (this.promedioElegidoCriterio == null) {
			throw new UserException("Debe elegir un filtrado de promedio");
		}

	}


	public double getPromedio() {
		return promedio;
	}


	public void setPromedio(double promedio) {
		this.promedio = promedio;
	}


	private Date fechaRango() {
		
		Date date = null;
if(fecha == null){
	date = Calendar.getInstance().getTime();
	}
else{	
	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	try {
		date = formatter.parse(fecha);
	} catch (ParseException e) {
		throw new UserException("La fecha deberá respetar el formato dd/mm/yyyy");
		
	}
	}
		return date;
	}


	public List<Jugador> getResultadoDeBusqueda() {
		//new RepositorioDeJugadores().getJugadores();
		return resultadoDeBusqueda;
	}

	/**
	 * @param resultadoDeBusqueda the resultadoDeBusqueda to set
	 */
	public void setResultadoDeBusqueda(List<Jugador> resultadoDeBusqueda) {
		this.resultadoDeBusqueda = resultadoDeBusqueda;
	}

public String getNombre() {
	return nombre;
}


public void setNombre(String nombre) {
	this.nombre = nombre;
}


public String getApodo() {
	return apodo;
}


public void setApodo(String apodo) {
	this.apodo = apodo;
}


public String getFecha() {
	return fecha;
}


public void setFecha(String fecha) {
	
	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	try {
		Date date = formatter.parse(fecha);
	} catch (ParseException e) {
		throw new UserException("La fecha deberá respetar el formato dd/mm/YYYY");
		
	}
	this.fecha = fecha;
	}
	



public List<ComboBoxSerial> getFiltroDeInfraccion() {
	List<ComboBoxSerial> list = new ArrayList<ComboBoxSerial>();
	
	list.add( new ComboBoxSerial("Solo los que tiene Infracciones",1));
	list.add( new ComboBoxSerial("Solo los que no tiene Infracciones",0));
	list.add( new ComboBoxSerial("Todos",-1));
	
	return list;
}


public int getHandicap() {
	return handicap;
}


public void setHandicap(int handicap) {
	this.handicap = handicap;
}

/**
 * @return the filtrosDeInfraccion
 */
public List<ComboBoxSerial> getFiltrosDeInfraccion() {
	return filtrosDeInfraccion;
}



public ComboBoxBoolean getHandicapElegidoCriterio() {
	return handicapElegidoCriterio;
}


public void setHandicapElegidoCriterio(ComboBoxBoolean handicapElegidoCriterio) {
	this.handicapElegidoCriterio = handicapElegidoCriterio;
}


public ComboBoxSerial getInfraccionElegidaCriterio() {
	return infraccionElegidaCriterio;
}


public void setInfraccionElegidaCriterio(
		ComboBoxSerial infraccionElegidaCriterio) {
	this.infraccionElegidaCriterio = infraccionElegidaCriterio;
}


public ComboBoxBoolean getPromedioElegidoCriterio() {
	return promedioElegidoCriterio;
}


public void setPromedioElegidoCriterio(ComboBoxBoolean promedioElegidoCriterio) {
	this.promedioElegidoCriterio = promedioElegidoCriterio;
}


public List<ComboBoxBoolean> getFiltrosDesdeHasta() {
	List<ComboBoxBoolean> list = new ArrayList<ComboBoxBoolean>();
	
	list.add(new ComboBoxBoolean("Desde",true));
	list.add( new ComboBoxBoolean("Hasta",false));
	return list;
}




public void setFiltrosDeInfraccion(List<ComboBoxSerial> filtrosDeInfraccion) {
	this.filtrosDeInfraccion = filtrosDeInfraccion;
}


/**
 * @return the jugadorSeleccionado
 */
public Jugador getJugadorSeleccionado() {
	return jugadorSeleccionado;
}


/**
 * @param jugadorSeleccionado the jugadorSeleccionado to set
 */
public void setJugadorSeleccionado(Jugador jugadorSeleccionado) {
	this.jugadorSeleccionado = jugadorSeleccionado;
}





}



