package ui.entidadesUtiles;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.futbol5.Infraccion;
import model.futbol5.Jugador;
import model.futbol5.Partido;
import model.inscripcion.Condicion;
import model.inscripcion.InscripcionCondicional;
import model.inscripcion.InscripcionEstandar;
import model.inscripcion.InscripcionSolidaria;
import model.ordenamiento.CriterioDeOrden;
import model.ordenamiento.CriterioHandicap;
import model.dividirEquipos.CriterioParaDividirEquipos;
import model.dividirEquipos.CriterioParesEImpares;


public class Repositorio {
	private static Repositorio instance;	
	private Partido partido;
	private List<Jugador> jugadores;

	private Jugador juan;
	private InscripcionEstandar inscripcionJuan;
	private Jugador esteban;
	private InscripcionEstandar inscripcionEsteban;
	private Jugador ramiro;
	private InscripcionEstandar inscripcionramiro;
	private Jugador mario;
	private InscripcionEstandar inscripcionmario;
	private Jugador adrian;
	private InscripcionEstandar inscripcionadrian;
	private Jugador marcos;
	private InscripcionEstandar inscripcionmarcos;
	private Jugador carlos;
	private InscripcionEstandar inscripcioncarlos;
	private Jugador turco;
	private	InscripcionEstandar inscripcionturco;
	private Jugador coqui;
	private	InscripcionEstandar inscripcioncoqui;
	private Jugador mati;
	private	InscripcionEstandar inscripcionmati;
	private Jugador jose;
	private InscripcionCondicional inscripcionJose;
	private Condicion condicionJose;
	private Jugador franco;
	private InscripcionCondicional inscripcionfranco;
	private Condicion condicionfranco;
	private Jugador dani;
	private InscripcionCondicional inscripciondani;
	private Condicion condiciondani;
	private Jugador maria;
	private InscripcionSolidaria inscripcionMaria;
	private Jugador gordo;
	private InscripcionSolidaria inscripciongordo;
	private CriterioHandicap criterioHandicap = new CriterioHandicap();
	private CriterioParesEImpares criterioParesEImpares = new CriterioParesEImpares();
	private Partido partidoAnterior;
	private Partido amistoso;

	
	

public Repositorio(){

	jugadores = new ArrayList<Jugador>();
	
	this.inicializarJugadores();
	this.agregarAmigosAJugador();
	this.inicializarInscripciones();
	this.partidosAnterioresPersistente(false);
	this.nuevoPartidoInit();
	
		
	}

public void nuevoPartidoInit() {
	LocalDate hoy=LocalDate.now();
	LocalTime hora=LocalTime.of(22,00);
	
	partido= new Partido(hoy,hora,"partidoPosta");
	partido.altaInscripcion(inscripcionJuan);
	partido.altaInscripcion(inscripcionEsteban);
	partido.altaInscripcion(inscripcionramiro);
	partido.altaInscripcion(inscripcionmario);
	partido.altaInscripcion(inscripcionadrian);
	partido.altaInscripcion(inscripciondani);
	partido.altaInscripcion(inscripcionfranco);
	partido.altaInscripcion(inscripcionJose);
	partido.altaInscripcion(inscripcionMaria);
	partido.altaInscripcion(inscripciongordo);
	
}

public void agregarAmigosAJugador() {
	esteban.agregarAmigo(juan);
	esteban.agregarAmigo(gordo);
	juan.agregarAmigo(adrian);
	juan.agregarAmigo(carlos);
	
}

public void inicializarInscripciones() {
	inscripcionJuan= new InscripcionEstandar(juan);
	inscripcionEsteban= new InscripcionEstandar(esteban);
	inscripcionramiro= new InscripcionEstandar(ramiro);
	inscripcionmario= new InscripcionEstandar(mario);
	inscripcionadrian= new InscripcionEstandar(adrian);
	inscripcionmarcos= new InscripcionEstandar(marcos);
	inscripcioncarlos= new InscripcionEstandar(carlos);
	inscripcionturco= new InscripcionEstandar(turco);
	inscripcioncoqui= new InscripcionEstandar(coqui);
	inscripcionmati= new InscripcionEstandar(mati);
	condicionJose = new Condicion();
	inscripcionJose= new InscripcionCondicional(jose,condicionJose);
	condicionfranco = new Condicion();
	inscripcionfranco= new InscripcionCondicional(franco,condicionfranco);
	condiciondani = new Condicion();
	inscripciondani= new InscripcionCondicional(dani,condiciondani);
	inscripcionMaria= new InscripcionSolidaria(maria);
	inscripciongordo= new InscripcionSolidaria(gordo);
	
}

public void inicializarJugadores() {
	juan = nuevoJugador(21,"Juan","Juancito","01/02/1992",10);
	esteban= nuevoJugador(21,"Esteban","Steve","01/02/1992",2);
	ramiro= nuevoJugador(21,"Ramiro","Rama","01/02/1992",3);
	mario= nuevoJugador(34,"Mario","It's My Mario!","01/02/1992",4);
	adrian= nuevoJugador(21,"Adrian","Adri","01/02/1992",5);
	marcos= nuevoJugador(27,"Marcos","Marco","01/02/1992",6);
	carlos= nuevoJugador(19,"Carlos","Charly","01/02/1992",2);
	turco= nuevoJugador(45,"Carlitos","Turco","01/02/1992",1);
	coqui= nuevoJugador(23,"Damian","Coqui","01/02/1992",6);
	mati= nuevoJugador(21,"Matias","Mati","01/02/1992",6);
	jose= nuevoJugador(21,"Jose Maria","Josema","01/02/1992",6);
	franco= nuevoJugador(32,"Franco","Fran","01/02/1992",7);
	dani= nuevoJugador(17,"Daniel","Dani","01/02/1992",8);
	maria= nuevoJugador(22,"Maria","Mary","01/02/1992",9);
	gordo= nuevoJugador(22,"Raul","Gordo","01/02/1992",10);
	
}

private Jugador nuevoJugador(int edad, String nombre, String apodo,String fecha, double handicap) {
	
	Date date = null;
	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	try {
		date = formatter.parse(fecha);
	} catch (ParseException e) {
		e.printStackTrace();
	}
	Jugador jugador = new Jugador(edad);
	jugador.setNombre(nombre);
	jugador.setFechaDeNacimiento(date);
	jugador.setApodo(apodo);
	jugador.setHandicap(handicap);
	this.jugadores.add(jugador);
	return jugador;
}

public Partido getPartido() {
	return partido;
}

public void setPartido(Partido partido) {
	this.partido = partido;
}


public List<Jugador> getJugadores() {
	return jugadores;
}


public void setJugadores(List<Jugador> jugadores) {
	this.jugadores = jugadores;
}



public static synchronized Repositorio getInstance() {
	if (instance == null) {
		Repositorio repo = new Repositorio();
		instance = repo;
	}
	return instance;
}

public List<Jugador> buscar(Jugador jugadorBuscado) {
	List<Jugador> resultados = new ArrayList<Jugador>();

	for (Jugador jugador : this.jugadores) {
		if (jugadorBuscado == null ||
				(this.matchearDatos(jugador,jugadorBuscado)
				&& this.fechaAnterior(jugador,jugadorBuscado)
				&& this.infraccion(jugador,jugadorBuscado)
				&& (jugadorBuscado.getHandicapCriterio() == null || ((jugadorBuscado.getHandicapCriterio() != null) && this.desdeHastaHandicap(jugador,jugadorBuscado)))
				&& (jugadorBuscado.getPromedioCriterio() == null || ((jugadorBuscado.getPromedioCriterio() != null) && this.desdeHastaPromedio(jugador,jugadorBuscado)))
				)) {
			
			
			resultados.add(jugador);
		}
	}
	return resultados;
}



private boolean desdeHastaPromedio(Jugador jugador, Jugador jugadorBuscado) {
	
	
	double promedioBuscado = jugadorBuscado.getPromedioBuscado();
	double promedio = jugador.getPromedioDeTodosLosPartido();
	
	if((jugadorBuscado.getPromedioCriterio().isBooleano() && promedio >  promedioBuscado)|| (!jugadorBuscado.getPromedioCriterio().isBooleano() && promedio <= promedioBuscado)){ //desde es true
		return true;
	}
	return false;
}



private boolean desdeHastaHandicap(Jugador jugador, Jugador jugadorBuscado) {

	double handicapBuscado = jugadorBuscado.getHandicap();
	double handicap = jugador.getHandicap();
	if((jugadorBuscado.getHandicapCriterio().isBooleano() && handicap >  handicapBuscado)|| (!jugadorBuscado.getHandicapCriterio().isBooleano() && handicap <= handicapBuscado)){ //desde es true
		return true;
	}
	return false;
}



private boolean infraccion(Jugador jugador, Jugador jugadorBuscado) {

	int infracciones = jugador.getInfracciones().size();
	
	int criterio = jugadorBuscado.getInfraccionesCriterio();
	
	if( (criterio < 0) || (infracciones == 0 && criterio == 0) || (infracciones > 0 && criterio > 0) ){
		return true;
		}

	
	return false;
}



private boolean fechaAnterior(Jugador jugador, Jugador jugadorBuscado) {
	

	if(jugadorBuscado.getFechaDeNacimientoDate() == null){
		return true;
	}
	if(jugador.getFechaDeNacimientoDate().before(jugadorBuscado.getFechaDeNacimientoDate())){
		return true;
	}
	return false;
}



private boolean matchearDatos(Jugador jugador, Jugador jugadorBuscado) {
	
if(	match(jugadorBuscado.getNombre(), jugador.getNombre()) && match(jugadorBuscado.getApodo(), jugador.getApodo())){
		return true;
	};
	return false;
}



protected boolean match(Object expectedValue, Object realValue) {
	return expectedValue == null
		|| realValue.toString().toLowerCase().contains(expectedValue.toString().toLowerCase());
}

public void partidosAnterioresPersistente(boolean persiste) {
	
	LocalDate hoy=LocalDate.now();
	LocalTime hora=LocalTime.of(22,00);
	
	partidoAnterior= new Partido(hoy,hora,"partidoAnteriorCalle");
	
	
	partidoAnterior.altaInscripcion(inscripcionJuan);
	partidoAnterior.altaInscripcion(inscripcionEsteban);
	partidoAnterior.altaInscripcion(inscripcionramiro);
	partidoAnterior.altaInscripcion(inscripcionmario);
	partidoAnterior.altaInscripcion(inscripcionadrian);
	partidoAnterior.altaInscripcion(inscripciondani);
	partidoAnterior.altaInscripcion(inscripcionfranco);
	partidoAnterior.altaInscripcion(inscripcionJose);
	partidoAnterior.altaInscripcion(inscripcionMaria);
	partidoAnterior.altaInscripcion(inscripciongordo);
	
	if(persiste){
	db.EntityManagerHelper.persist(criterioHandicap);
	db.EntityManagerHelper.persist(criterioParesEImpares);
	}
	
	partidoAnterior.setCriterioDeOrden(criterioHandicap);
	partidoAnterior.setCriterioParaDividirEquipos(criterioParesEImpares);
	partidoAnterior.generarEquipos(partidoAnterior.ordenarPrimeros10());
	partidoAnterior.equiposConfirmados();
	
	if(persiste){
	db.EntityManagerHelper.persist(partidoAnterior);
	}
	
	esteban.calificarA(juan,partidoAnterior,"bla",2,persiste);
	ramiro.calificarA(juan,partidoAnterior,"bla",4,persiste);
	juan.calificarA(esteban,partidoAnterior,"bla",2,persiste);
	esteban.calificarA(esteban,partidoAnterior,"bla",2,persiste);
	juan.calificarA(ramiro,partidoAnterior,"bla",4,persiste);
	esteban.calificarA(ramiro,partidoAnterior,"bla",4,persiste);
	juan.calificarA(mario,partidoAnterior,"bla",5,persiste);
	esteban.calificarA(mario,partidoAnterior,"bla",5,persiste);
	juan.calificarA(adrian,partidoAnterior,"bla",6,persiste);
	esteban.calificarA(adrian,partidoAnterior,"bla",6,persiste);
	juan.calificarA(dani,partidoAnterior,"bla",7,persiste);
	esteban.calificarA(dani,partidoAnterior,"bla",7,persiste);
	juan.calificarA(franco,partidoAnterior,"bla",8,persiste);
	esteban.calificarA(franco,partidoAnterior,"bla",8,persiste);
	juan.calificarA(jose,partidoAnterior,"bla",9,persiste);
	esteban.calificarA(jose,partidoAnterior,"bla",9,persiste);
	juan.calificarA(maria,partidoAnterior,"bla",9,persiste);
	esteban.calificarA(maria,partidoAnterior,"bla",10,persiste);
	juan.calificarA(gordo,partidoAnterior,"bla",10,persiste);
	esteban.calificarA(gordo,partidoAnterior,"bla",10,persiste);
	//calificarA me lo persiste adentro.
	
	db.EntityManagerHelper.commit();

	db.EntityManagerHelper.beginTransaction();
	amistoso =  new Partido(hoy,hora,"partidoAmistoso");
	
	amistoso.altaInscripcion(inscripcionJuan);
	amistoso.altaInscripcion(inscripcionEsteban);
	amistoso.altaInscripcion(inscripcionramiro);
	amistoso.altaInscripcion(inscripcionmario);
	amistoso.altaInscripcion(inscripcionadrian);
	amistoso.altaInscripcion(inscripciondani);
	amistoso.altaInscripcion(inscripcionfranco);
	amistoso.altaInscripcion(inscripcionJose);
	amistoso.altaInscripcion(inscripcionMaria);
	amistoso.altaInscripcion(inscripciongordo);
	

	amistoso.setCriterioDeOrden(criterioHandicap);
	amistoso.setCriterioParaDividirEquipos(criterioParesEImpares);
	amistoso.generarEquipos(amistoso.ordenarPrimeros10());
	amistoso.equiposConfirmados();
	
	if(persiste){
		db.EntityManagerHelper.persist(amistoso);
	}
	esteban.calificarA(juan,amistoso,"bla",1,persiste);
	ramiro.calificarA(juan,amistoso,"bla",3,persiste);
	juan.calificarA(esteban,amistoso,"bla",5,persiste);
	esteban.calificarA(esteban,amistoso,"bla",6,persiste);
	juan.calificarA(ramiro,amistoso,"bla",6,persiste);
	esteban.calificarA(ramiro,amistoso,"bla",10,persiste);
	juan.calificarA(mario,amistoso,"bla",1,persiste);
	esteban.calificarA(mario,amistoso,"bla",2,persiste);
	juan.calificarA(adrian,amistoso,"bla",3,persiste);
	esteban.calificarA(adrian,amistoso,"bla",6,persiste);
	juan.calificarA(dani,amistoso,"bla",8,persiste);
	esteban.calificarA(dani,amistoso,"bla",1,persiste);
	juan.calificarA(franco,amistoso,"bla",2,persiste);
	esteban.calificarA(franco,amistoso,"bla",3,persiste);
	juan.calificarA(jose,amistoso,"bla",9,persiste);
	esteban.calificarA(jose,amistoso,"bla",9,persiste);
	juan.calificarA(maria,amistoso,"bla",4,persiste);
	esteban.calificarA(maria,amistoso,"bla",6,persiste);
	juan.calificarA(gordo,amistoso,"bla",10,persiste);
	esteban.calificarA(gordo,amistoso,"bla",10,persiste);
	
	Infraccion infraccionAJuan = new Infraccion(amistoso,"Llego tarde");
	
	if(persiste){
		db.EntityManagerHelper.persist(infraccionAJuan);
	}
	
	juan.getInfracciones().add(infraccionAJuan);
	
	db.EntityManagerHelper.commit();
}

public void persistirJugadores() {
	
	db.EntityManagerHelper.persist(juan);
	db.EntityManagerHelper.persist(esteban);
	db.EntityManagerHelper.persist(ramiro);
	db.EntityManagerHelper.persist(mario);
	db.EntityManagerHelper.persist(adrian);
	db.EntityManagerHelper.persist(marcos);
	db.EntityManagerHelper.persist(carlos);
	db.EntityManagerHelper.persist(turco);
	db.EntityManagerHelper.persist(coqui);
	db.EntityManagerHelper.persist(mati);
	db.EntityManagerHelper.persist(jose);
	db.EntityManagerHelper.persist(franco);
	db.EntityManagerHelper.persist(dani);
	db.EntityManagerHelper.persist(maria);
	db.EntityManagerHelper.persist(gordo);

}

public void persistirInscripciones() {
	
	this.inicializarInscripciones();
	db.EntityManagerHelper.persist(inscripcionJuan);
	db.EntityManagerHelper.persist(inscripcionEsteban);
	db.EntityManagerHelper.persist(inscripcionramiro);
	db.EntityManagerHelper.persist(inscripcionmario);
	db.EntityManagerHelper.persist(inscripcionadrian);
	db.EntityManagerHelper.persist(inscripcionmarcos);
	db.EntityManagerHelper.persist(inscripcioncarlos);
	db.EntityManagerHelper.persist(inscripcionturco);
	db.EntityManagerHelper.persist(inscripcioncoqui);
	db.EntityManagerHelper.persist(inscripcionmati);
	db.EntityManagerHelper.persist(inscripcionMaria);
	db.EntityManagerHelper.persist(inscripciongordo);	
	
	condicionJose = new Condicion();
	db.EntityManagerHelper.persist(condicionJose);
	inscripcionJose= new InscripcionCondicional(jose,condicionJose);
	db.EntityManagerHelper.persist(inscripcionJose);
	
	condicionfranco = new Condicion();
	db.EntityManagerHelper.persist(condicionfranco);
	inscripcionfranco= new InscripcionCondicional(franco,condicionfranco);
	db.EntityManagerHelper.persist(inscripcionfranco);
	
	condiciondani = new Condicion();
	db.EntityManagerHelper.persist(condiciondani);
	inscripciondani= new InscripcionCondicional(dani,condiciondani);
	db.EntityManagerHelper.persist(inscripciondani);



}

public void persistirPartido(CriterioDeOrden ordenamientoSeleccionado,
		CriterioParaDividirEquipos criterioSeleccionado,
		int ultimosPartidosSeleccionados) {
	db.EntityManagerHelper.beginTransaction();
	
	this.nuevoPartidoInit();
	ordenamientoSeleccionado.setPartidos(ultimosPartidosSeleccionados);
	
	db.EntityManagerHelper.persist(criterioSeleccionado);
	db.EntityManagerHelper.persist(ordenamientoSeleccionado);
	
	partido.setCriterioDeOrden(ordenamientoSeleccionado);
	partido.setCriterioParaDividirEquipos(criterioSeleccionado);
	partido.generarEquipos(partido.ordenarPrimeros10());

	partido.equiposConfirmados();
	
	db.EntityManagerHelper.persist(partido);
	db.EntityManagerHelper.commit();
	
}





}

