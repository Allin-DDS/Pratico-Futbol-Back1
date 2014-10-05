package testTPA;

import ordenamiento.CriterioHandicap;
import ordenamiento.CriterioUltimasNCalificaciones;
import ordenamiento.MixDeCriterios;
import ordenamiento.CriterioCalificacionesUltimoPartido;
import futbol5.Jugador;
import futbol5.Partido;
import dividirEquipos.CriterioParesEImpares;
import inscripcion.Condicion;
import inscripcion.Inscripcion;
import inscripcion.InscripcionCondicional;
import inscripcion.InscripcionEstandar;
import inscripcion.InscripcionSolidaria;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test; 

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class testOrdenarYGenerarEquipos {

	private Partido semifinal;
	private Partido pfinal;
	private Partido amistoso; 
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

	private CriterioHandicap criterioHandicap;
	private CriterioUltimasNCalificaciones criterioUltimas2Calificaciones;
	private CriterioUltimasNCalificaciones criterioUltimaCalificacion;
	private CriterioCalificacionesUltimoPartido criterioCalificacionesUltimoPartido;
	private MixDeCriterios mixHandicapYUltimaCalificacion;
	private CriterioParesEImpares criterioParesEImpares;

	@Before
	public void init(){
		LocalDate antesDeAyer=LocalDate.of(2014,9,12);
		LocalDate ayer=LocalDate.of(2014,9,13);
		LocalDate hoy=LocalDate.now();
		LocalTime hora=LocalTime.of(22,00);
		semifinal= new Partido(antesDeAyer,hora,"calleFalsa1234");
		pfinal= new Partido(ayer,hora,"calleFalsa1234");
		amistoso =  new Partido(hoy,hora,"calleFalsa1234");

		juan= new Jugador(21);
		juan.setHandicap(1);
		inscripcionJuan= new InscripcionEstandar(juan);
		esteban= new Jugador(21);
		esteban.setHandicap(2);
		inscripcionEsteban= new InscripcionEstandar(esteban);
		ramiro= new Jugador(21);
		ramiro.setHandicap(3);
		inscripcionramiro= new InscripcionEstandar(ramiro);
		mario= new Jugador(21);
		mario.setHandicap(4);
		inscripcionmario= new InscripcionEstandar(mario);
		adrian= new Jugador(21);
		adrian.setHandicap(5);
		inscripcionadrian= new InscripcionEstandar(adrian);

		jose= new Jugador(21);
		jose.setHandicap(6);
		condicionJose = new Condicion();
		inscripcionJose= new InscripcionCondicional(jose,condicionJose);
		franco= new Jugador(21);
		franco.setHandicap(7);
		condicionfranco = new Condicion();
		inscripcionfranco= new InscripcionCondicional(franco,condicionfranco);
		dani= new Jugador(21);
		dani.setHandicap(8);
		condiciondani = new Condicion();
		inscripciondani= new InscripcionCondicional(dani,condiciondani);

		maria= new Jugador(21);
		maria.setHandicap(9);
		inscripcionMaria= new InscripcionSolidaria(maria);
		gordo= new Jugador(21);
		gordo.setHandicap(10);
		inscripciongordo= new InscripcionSolidaria(gordo);
		criterioHandicap= new CriterioHandicap();
		criterioUltimas2Calificaciones= new CriterioUltimasNCalificaciones(2);
		criterioUltimaCalificacion= new CriterioUltimasNCalificaciones(1);
		criterioCalificacionesUltimoPartido= new CriterioCalificacionesUltimoPartido();
		mixHandicapYUltimaCalificacion= new MixDeCriterios();
		criterioParesEImpares= new CriterioParesEImpares();
		
		semifinal.altaInscripcion(inscripcionJuan);
		semifinal.altaInscripcion(inscripcionEsteban);
		semifinal.altaInscripcion(inscripcionramiro);
		semifinal.altaInscripcion(inscripcionmario);
		semifinal.altaInscripcion(inscripcionadrian);
		semifinal.altaInscripcion(inscripciondani);
		semifinal.altaInscripcion(inscripcionfranco);
		semifinal.altaInscripcion(inscripcionJose);
		semifinal.altaInscripcion(inscripcionMaria);
		semifinal.altaInscripcion(inscripciongordo);
		
		pfinal.altaInscripcion(inscripcionJuan);
		pfinal.altaInscripcion(inscripcionEsteban);
		pfinal.altaInscripcion(inscripcionramiro);
		pfinal.altaInscripcion(inscripcionmario);
		pfinal.altaInscripcion(inscripcionadrian);
		pfinal.altaInscripcion(inscripciondani);
		pfinal.altaInscripcion(inscripcionfranco);
		pfinal.altaInscripcion(inscripcionJose);
		pfinal.altaInscripcion(inscripcionMaria);
		pfinal.altaInscripcion(inscripciongordo);
		
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
	}

	@Test
	public void ordenarInscriptosConCriterioHandicap(){
		semifinal.setCriterioDeOrden(criterioHandicap);
		semifinal.setCriterioParaDividirEquipos(criterioParesEImpares);
		PriorityQueue<Inscripcion> primeros10Ordenados = semifinal.ordenarPrimeros10();

		assertTrue(primeros10Ordenados.poll()==inscripcionJuan);
		assertTrue(primeros10Ordenados.poll()==inscripcionEsteban);
		assertTrue(primeros10Ordenados.poll()==inscripcionramiro);
		assertTrue(primeros10Ordenados.poll()==inscripcionmario);
		assertTrue(primeros10Ordenados.poll()==inscripcionadrian);
		assertTrue(primeros10Ordenados.poll()==inscripcionJose);
		assertTrue(primeros10Ordenados.poll()==inscripcionfranco);
		assertTrue(primeros10Ordenados.poll()==inscripciondani);
		assertTrue(primeros10Ordenados.poll()==inscripcionMaria);
		assertTrue(primeros10Ordenados.poll()==inscripciongordo);

	}

	@Test
	public void ordenarInscriptosConCriterioUltimas2Calif(){
		semifinal.setCriterioDeOrden(criterioHandicap);
		semifinal.setCriterioParaDividirEquipos(criterioParesEImpares);
		semifinal.generarEquipos(semifinal.ordenarPrimeros10());
		semifinal.equiposConfirmados();
		
		esteban.calificarA(juan,semifinal,"bla",4);
		ramiro.calificarA(juan,semifinal,"bla",2);
		juan.calificarA(esteban,semifinal,"bla",2);
		esteban.calificarA(esteban,semifinal,"bla",2);
		juan.calificarA(ramiro,semifinal,"bla",3);
		esteban.calificarA(ramiro,semifinal,"bla",4);
		juan.calificarA(mario,semifinal,"bla",4);
		esteban.calificarA(mario,semifinal,"bla",5);
		juan.calificarA(adrian,semifinal,"bla",5);
		esteban.calificarA(adrian,semifinal,"bla",6);
		juan.calificarA(dani,semifinal,"bla",6);
		esteban.calificarA(dani,semifinal,"bla",7);
		juan.calificarA(franco,semifinal,"bla",7);
		esteban.calificarA(franco,semifinal,"bla",8);
		juan.calificarA(jose,semifinal,"bla",9);
		esteban.calificarA(jose,semifinal,"bla",9);
		juan.calificarA(maria,semifinal,"bla",9);
		esteban.calificarA(maria,semifinal,"bla",10);
		juan.calificarA(gordo,semifinal,"bla",10);
		esteban.calificarA(gordo,semifinal,"bla",10);

		pfinal.setCriterioDeOrden(criterioUltimas2Calificaciones);
		PriorityQueue<Inscripcion> primeros10Ordenados = pfinal.ordenarPrimeros10();	
		assertTrue(primeros10Ordenados.poll()==inscripcionEsteban);
		assertTrue(primeros10Ordenados.poll()==inscripcionJuan);
		assertTrue(primeros10Ordenados.poll()==inscripcionramiro);
		assertTrue(primeros10Ordenados.poll()==inscripcionmario);
		assertTrue(primeros10Ordenados.poll()==inscripcionadrian);
		assertTrue(primeros10Ordenados.poll()==inscripciondani);
		assertTrue(primeros10Ordenados.poll()==inscripcionfranco);
		assertTrue(primeros10Ordenados.poll()==inscripcionJose);
		assertTrue(primeros10Ordenados.poll()==inscripcionMaria);
		assertTrue(primeros10Ordenados.poll()==inscripciongordo);
	}

	@Test
	public void ordenarInscriptosConCriterioCalificaacionesUltimoPartido(){
		semifinal.setCriterioDeOrden(criterioHandicap);
		semifinal.setCriterioParaDividirEquipos(criterioParesEImpares);
		semifinal.generarEquipos(semifinal.ordenarPrimeros10());
		semifinal.equiposConfirmados();
		esteban.calificarA(juan,semifinal,"bla",1);
		ramiro.calificarA(juan,semifinal,"bla",2);
		juan.calificarA(esteban,semifinal,"bla",3);
		esteban.calificarA(esteban,semifinal,"bla",4);
		juan.calificarA(ramiro,semifinal,"bla",5);
		esteban.calificarA(ramiro,semifinal,"bla",6);
		juan.calificarA(mario,semifinal,"bla",1);
		esteban.calificarA(mario,semifinal,"bla",1);
		juan.calificarA(adrian,semifinal,"bla",8);
		esteban.calificarA(adrian,semifinal,"bla",5);
		juan.calificarA(dani,semifinal,"bla",9);
		esteban.calificarA(dani,semifinal,"bla",8);
		juan.calificarA(franco,semifinal,"bla",8);
		esteban.calificarA(franco,semifinal,"bla",8);
		juan.calificarA(jose,semifinal,"bla",2);
		esteban.calificarA(jose,semifinal,"bla",9);
		juan.calificarA(maria,semifinal,"bla",1);
		esteban.calificarA(maria,semifinal,"bla",3);
		juan.calificarA(gordo,semifinal,"bla",7);
		esteban.calificarA(gordo,semifinal,"bla",8);
		
		pfinal.setCriterioDeOrden(criterioHandicap);
		pfinal.setCriterioParaDividirEquipos(criterioParesEImpares);
		pfinal.generarEquipos(pfinal.ordenarPrimeros10());
		pfinal.equiposConfirmados();
		esteban.calificarA(juan,pfinal,"bla",2);
		ramiro.calificarA(juan,pfinal,"bla",4);
		juan.calificarA(esteban,pfinal,"bla",2);
		esteban.calificarA(esteban,pfinal,"bla",2);
		juan.calificarA(ramiro,pfinal,"bla",4);
		esteban.calificarA(ramiro,pfinal,"bla",4);
		juan.calificarA(mario,pfinal,"bla",5);
		esteban.calificarA(mario,pfinal,"bla",5);
		juan.calificarA(adrian,pfinal,"bla",6);
		esteban.calificarA(adrian,pfinal,"bla",6);
		juan.calificarA(dani,pfinal,"bla",7);
		esteban.calificarA(dani,pfinal,"bla",7);
		juan.calificarA(franco,pfinal,"bla",8);
		esteban.calificarA(franco,pfinal,"bla",8);
		juan.calificarA(jose,pfinal,"bla",9);
		esteban.calificarA(jose,pfinal,"bla",9);
		juan.calificarA(maria,pfinal,"bla",9);
		esteban.calificarA(maria,pfinal,"bla",10);
		juan.calificarA(gordo,pfinal,"bla",10);
		esteban.calificarA(gordo,pfinal,"bla",10);

		amistoso.setCriterioDeOrden(criterioCalificacionesUltimoPartido);
		PriorityQueue<Inscripcion> primeros10Ordenados = amistoso.ordenarPrimeros10();
		assertTrue(primeros10Ordenados.poll()==inscripcionEsteban);
		assertTrue(primeros10Ordenados.poll()==inscripcionJuan);
		assertTrue(primeros10Ordenados.poll()==inscripcionramiro);
		assertTrue(primeros10Ordenados.poll()==inscripcionmario);
		assertTrue(primeros10Ordenados.poll()==inscripcionadrian);
		assertTrue(primeros10Ordenados.poll()==inscripciondani);
		assertTrue(primeros10Ordenados.poll()==inscripcionfranco);
		assertTrue(primeros10Ordenados.poll()==inscripcionJose);
		assertTrue(primeros10Ordenados.poll()==inscripcionMaria);
		assertTrue(primeros10Ordenados.poll()==inscripciongordo);
	}
	
	@Test
	public void ordenarInscriptosConMixHandicapYCriterioUltimaCalificacion(){		
		pfinal.setCriterioDeOrden(criterioHandicap);
		pfinal.setCriterioParaDividirEquipos(criterioParesEImpares);
		pfinal.generarEquipos(pfinal.ordenarPrimeros10());
		pfinal.equiposConfirmados();
		ramiro.calificarA(juan,pfinal,"bla",4);
		esteban.calificarA(juan,pfinal,"bla",1);
		juan.calificarA(esteban,pfinal,"bla",9);
		esteban.calificarA(esteban,pfinal,"bla",2);
		juan.calificarA(ramiro,pfinal,"bla",2);
		esteban.calificarA(ramiro,pfinal,"bla",3);
		juan.calificarA(mario,pfinal,"bla",3);
		esteban.calificarA(mario,pfinal,"bla",4);
		juan.calificarA(adrian,pfinal,"bla",8);
		esteban.calificarA(adrian,pfinal,"bla",5);
		juan.calificarA(jose,pfinal,"bla",5);
		esteban.calificarA(jose,pfinal,"bla",6);
		juan.calificarA(franco,pfinal,"bla",10);
		esteban.calificarA(franco,pfinal,"bla",7);
		juan.calificarA(dani,pfinal,"bla",8);
		esteban.calificarA(dani,pfinal,"bla",8);
		juan.calificarA(maria,pfinal,"bla",9);
		esteban.calificarA(maria,pfinal,"bla",9);
		juan.calificarA(gordo,pfinal,"bla",8);
		esteban.calificarA(gordo,pfinal,"bla",10);

		mixHandicapYUltimaCalificacion.agregarCriterioDeOrden(criterioHandicap);
		mixHandicapYUltimaCalificacion.agregarCriterioDeOrden(criterioUltimaCalificacion);
		amistoso.setCriterioDeOrden(mixHandicapYUltimaCalificacion);
		PriorityQueue<Inscripcion> primeros10Ordenados = amistoso.ordenarPrimeros10();
		
		assertTrue(primeros10Ordenados.poll()==inscripcionJuan);
		assertTrue(primeros10Ordenados.poll()==inscripcionEsteban);
		assertTrue(primeros10Ordenados.poll()==inscripcionramiro);
		assertTrue(primeros10Ordenados.poll()==inscripcionmario);
		assertTrue(primeros10Ordenados.poll()==inscripcionadrian);
		assertTrue(primeros10Ordenados.poll()==inscripcionJose);
		assertTrue(primeros10Ordenados.poll()==inscripcionfranco);
		assertTrue(primeros10Ordenados.poll()==inscripciondani);
		assertTrue(primeros10Ordenados.poll()==inscripcionMaria);
		assertTrue(primeros10Ordenados.poll()==inscripciongordo);
	}
	
	@Test
	public void generarEquiposConCriterioParEImpar(){
		semifinal.setCriterioDeOrden(criterioHandicap);
		semifinal.setCriterioParaDividirEquipos(criterioParesEImpares);
		semifinal.generarEquipos(semifinal.ordenarPrimeros10());
		Collection<Inscripcion> equipo1Esperado = new LinkedList<>();
		equipo1Esperado.add(inscripcionJuan);
		equipo1Esperado.add(inscripcionramiro);
		equipo1Esperado.add(inscripcionadrian);
		equipo1Esperado.add(inscripcionfranco);
		equipo1Esperado.add(inscripcionMaria);
		Collection<Inscripcion> equipo2Esperado = new LinkedList<>();
		equipo2Esperado.add(inscripcionEsteban);
		equipo2Esperado.add(inscripcionmario);
		equipo2Esperado.add(inscripciondani);
		equipo2Esperado.add(inscripcionJose);
		equipo2Esperado.add(inscripciongordo);
		assertTrue(semifinal.getEquipo1().containsAll(equipo1Esperado));
		assertTrue(semifinal.getEquipo2().containsAll(equipo2Esperado));
	}

}