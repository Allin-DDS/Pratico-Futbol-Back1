package testTPA;

import notificaciones.MailAAdministrador;
import notificaciones.MailAAmigos;
import futbol5.Jugador;
import futbol5.Partido;
import dividirEquipos.CriterioParesEImpares;
import inscripcion.Condicion;
import inscripcion.InscripcionCondicional;
import inscripcion.InscripcionEstandar;
import inscripcion.InscripcionSolidaria;
import excepciones.Hay10EstandarException;
import excepciones.NoHay10InscriptosParaGenerarEquiposException;
import excepciones.EquiposConfirmadosException;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import ordenamiento.CriterioHandicap;

import org.junit.Before;
import org.junit.Test; 

import java.time.LocalDate;
import java.time.LocalTime;


public class TestTPA {
	private Partido pfinal;
	private Partido semifinal;
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
	
	private MailAAdministrador mailAAdministradorMock;
	private MailAAmigos mailAAmigosMock;

	private CriterioParesEImpares criterioParesEImpares;
	private CriterioHandicap criterioHandicap;
	
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
		marcos= new Jugador(21);
		inscripcionmarcos= new InscripcionEstandar(marcos);
		carlos= new Jugador(21);
		inscripcioncarlos= new InscripcionEstandar(carlos);
		turco= new Jugador(21);	
		inscripcionturco= new InscripcionEstandar(turco);
		coqui= new Jugador(21);
		inscripcioncoqui= new InscripcionEstandar(coqui);
		mati= new Jugador(21);
		inscripcionmati= new InscripcionEstandar(mati);

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
		
		mailAAdministradorMock= mock(MailAAdministrador.class);
		mailAAmigosMock= mock(MailAAmigos.class);
		criterioHandicap= new CriterioHandicap();
		criterioParesEImpares= new CriterioParesEImpares();
	}
	
	@Test 
	public void agregar1Condicional_ContarLosCondicionales(){
		semifinal.altaInscripcion(inscripcionJose);
		assertEquals(1,semifinal.cantidadInscriptosCondicionales());
	}
	
	@Test 
	public void agregar2Estandar_ContarLosEstandar(){
		semifinal.altaInscripcion(inscripcionJuan);
		semifinal.altaInscripcion(inscripcionEsteban);
		assertEquals(2,semifinal.cantidadInscriptosEstandar());
	}
	
	@Test 
	public void agregar2Solidarios_ContarLosSolidarios(){
		semifinal.altaInscripcion(inscripciongordo);
		semifinal.altaInscripcion(inscripcionMaria);
		assertEquals(2,semifinal.cantidadInscriptosSolidarios());
	}
	
	
	@Test 
	public void agregar2Estandar2SolidariaY1Condicional_cantidadTotalInscriptos(){
		semifinal.altaInscripcion(inscripcionJuan);
		semifinal.altaInscripcion(inscripcionEsteban);
		
		semifinal.altaInscripcion(inscripcionJose);
		
		semifinal.altaInscripcion(inscripcionMaria);
		semifinal.altaInscripcion(inscripciongordo);
		
		assertEquals(5,semifinal.cantidadTotalInscriptos());
	}
	
	@Test
	public void agregar2Solidarios1Estandar1Condicional_VerificarOrdenCorrecto(){
		semifinal.altaInscripcion(inscripcionMaria);
		semifinal.altaInscripcion(inscripciongordo);
		semifinal.altaInscripcion(inscripcionJuan);
		semifinal.altaInscripcion(inscripcionJose);
		
		assertEquals(inscripcionJuan,semifinal.getInscripciones().poll());
		assertEquals(inscripcionJose,semifinal.getInscripciones().poll());
		assertEquals(inscripciongordo,semifinal.getInscripciones().poll());
		assertEquals(inscripcionMaria,semifinal.getInscripciones().poll());		
	}
	
	@Test (expected=Hay10EstandarException.class)
	public void agregar10EstandarY1Solidaria_Hay10EstandarException(){
		semifinal.altaInscripcion(inscripcionJuan);
		semifinal.altaInscripcion(inscripcionEsteban);
		semifinal.altaInscripcion(inscripcionramiro);
		semifinal.altaInscripcion(inscripcionmario);
		semifinal.altaInscripcion(inscripcionadrian);
		semifinal.altaInscripcion(inscripcionmarcos);
		semifinal.altaInscripcion(inscripcioncarlos);
		semifinal.altaInscripcion(inscripcionturco);
		semifinal.altaInscripcion(inscripcioncoqui);
		semifinal.altaInscripcion(inscripcionmati);
		
		semifinal.altaInscripcion(inscripcionMaria);
	}
	
	@Test
	public void reemplazo1SolidarioSinSustituto_AgregarInfraccion(){
		semifinal.altaInscripcion(inscripcionMaria);
		semifinal.BajaInscripcion(inscripcionMaria,null);
		
		assertEquals(1,maria.getCantidadInfracPorNoTenerSustituto());	
	}
	
	@Test
	public void agregarInscripcion_AvisarAAdmin(){
		semifinal.altaInscripcion(inscripcionJuan);
		mailAAdministradorMock.notificarNuevaInscripcion(juan,semifinal);
		verify(mailAAdministradorMock).notificarNuevaInscripcion(juan,semifinal);
	}
	
	@Test
	public void agregarInscripcion_AvisarAAMigos(){
		semifinal.altaInscripcion(inscripcionJuan);
		mailAAmigosMock.notificarNuevaInscripcion(juan,semifinal);
		verify(mailAAmigosMock).notificarNuevaInscripcion(juan,semifinal);
	}
	
	@Test
	public void reemplazarInscripcionSinSusitucion_AvisarAAdmin(){
		semifinal.altaInscripcion(inscripcionJuan);
		semifinal.BajaInscripcion(inscripcionJuan, null);
		mailAAdministradorMock.notificarReemplazoDeInscSinSustituto(semifinal);
		verify(mailAAdministradorMock).notificarReemplazoDeInscSinSustituto(semifinal);
	}
	
	@Test
	public void reemplazarInscripcionSinSusitucion_AvisarAAmigos(){
		semifinal.altaInscripcion(inscripcionJuan);
		semifinal.BajaInscripcion(inscripcionJuan, null);
		mailAAmigosMock.notificarReemplazoDeInscSinSustituto(semifinal);
		verify(mailAAmigosMock).notificarReemplazoDeInscSinSustituto(semifinal);
	}
	
	@Test
	public void partidoConfirmado_AvisarAAdmin(){
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
		
		mailAAdministradorMock.notificarPartidoConfirmado(semifinal);
		verify(mailAAdministradorMock).notificarPartidoConfirmado(semifinal);
	}
	
	@Test
	public void partidoDesconfirmado_AvisarAAdmin(){
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
		semifinal.BajaInscripcion(inscripciongordo,null);
		
		mailAAdministradorMock.notificarPartidoDesconfirmado(semifinal);
		verify(mailAAdministradorMock).notificarPartidoDesconfirmado(semifinal);
	}
	
	@Test(expected=NoHay10InscriptosParaGenerarEquiposException.class)
	public void agregar5Estandar3CondicionalYGenerarEquipos_SeGeneraExcepcionPqNoHay10Inscriptos(){
		semifinal.altaInscripcion(inscripcionJuan);
		semifinal.altaInscripcion(inscripcionEsteban);
		semifinal.altaInscripcion(inscripcionramiro);
		semifinal.altaInscripcion(inscripcionmario);
		semifinal.altaInscripcion(inscripcionadrian);
	
		semifinal.altaInscripcion(inscripciondani);
		semifinal.altaInscripcion(inscripcionfranco);
		semifinal.altaInscripcion(inscripcionJose);
		
		semifinal.generarEquipos(semifinal.getInscripciones());
}	
	
	@Test(expected=EquiposConfirmadosException.class)
	public void AdministradorConfirmaEquiposYSeAgregaInscripcion_SeGeneraExcepcion(){
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
		semifinal.setCriterioDeOrden(criterioHandicap);
		semifinal.setCriterioParaDividirEquipos(criterioParesEImpares);
		semifinal.generarEquipos(semifinal.ordenarPrimeros10());
		semifinal.equiposConfirmados();
		semifinal.altaInscripcion(inscripciondani);
	}	
	
	@Test
	public void promedioDeUltimoPartido(){
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
		semifinal.setCriterioDeOrden(criterioHandicap);
		semifinal.setCriterioParaDividirEquipos(criterioParesEImpares);
		semifinal.generarEquipos(semifinal.ordenarPrimeros10());
		semifinal.equiposConfirmados();
		esteban.calificarA(juan, semifinal, "ceack",10);
		ramiro.calificarA(juan, semifinal, "ceack",8);
		
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
		pfinal.setCriterioDeOrden(criterioHandicap);
		pfinal.setCriterioParaDividirEquipos(criterioParesEImpares);
		pfinal.generarEquipos(pfinal.ordenarPrimeros10());
		pfinal.equiposConfirmados();
		esteban.calificarA(juan, pfinal, "pobre",5);
		ramiro.calificarA(juan, pfinal, "pobre",5);
		
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
		amistoso.generarEquipos(pfinal.ordenarPrimeros10());
		amistoso.equiposConfirmados();
		esteban.calificarA(juan, amistoso, "pobre",1);
		ramiro.calificarA(juan, amistoso, "pobre",3);
		
		assertEquals(2,juan.getPromedioDeUltimoPartido(),0);
	}
	@Test
	public void promedioDeUltimoPartido2(){
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
		semifinal.setCriterioDeOrden(criterioHandicap);
		semifinal.setCriterioParaDividirEquipos(criterioParesEImpares);
		semifinal.generarEquipos(semifinal.ordenarPrimeros10());
		semifinal.equiposConfirmados();
		esteban.calificarA(juan, semifinal, "ceack",10);
		ramiro.calificarA(juan, semifinal, "ceack",8);
		
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
		pfinal.setCriterioDeOrden(criterioHandicap);
		pfinal.setCriterioParaDividirEquipos(criterioParesEImpares);
		pfinal.generarEquipos(pfinal.ordenarPrimeros10());
		pfinal.equiposConfirmados();
		esteban.calificarA(juan, pfinal, "pobre",5);
		ramiro.calificarA(juan, pfinal, "pobre",5);
		
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
		amistoso.generarEquipos(pfinal.ordenarPrimeros10());
//		amistoso.equiposConfirmados();
//		esteban.calificarA(juan, amistoso, "pobre",1);
//		ramiro.calificarA(juan, amistoso, "pobre",3);
		
		assertEquals(5,juan.getPromedioDeUltimoPartido(),0);
	}
	@Test
	public void promedioDeTodosLosPartido(){
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
		semifinal.setCriterioDeOrden(criterioHandicap);
		semifinal.setCriterioParaDividirEquipos(criterioParesEImpares);
		semifinal.generarEquipos(semifinal.ordenarPrimeros10());
		semifinal.equiposConfirmados();
		esteban.calificarA(juan, semifinal, "ceack",10);
		ramiro.calificarA(juan, semifinal, "ceack",5);
		
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
		pfinal.setCriterioDeOrden(criterioHandicap);
		pfinal.setCriterioParaDividirEquipos(criterioParesEImpares);
		pfinal.generarEquipos(pfinal.ordenarPrimeros10());
		pfinal.equiposConfirmados();
		esteban.calificarA(juan, pfinal, "pobre",5);
		ramiro.calificarA(juan, pfinal, "pobre",10);
		assertEquals(7.5,juan.getPromedioDeTodosLosPartido(),0);	
	}
	
	@Test
	public void cantidadDePartidosQueJugo(){
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
		semifinal.setCriterioDeOrden(criterioHandicap);
		semifinal.setCriterioParaDividirEquipos(criterioParesEImpares);
		semifinal.generarEquipos(semifinal.ordenarPrimeros10());
		semifinal.equiposConfirmados();
		
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
		pfinal.setCriterioDeOrden(criterioHandicap);
		pfinal.setCriterioParaDividirEquipos(criterioParesEImpares);
		pfinal.generarEquipos(pfinal.ordenarPrimeros10());
		pfinal.equiposConfirmados();
		
		assertEquals(2,juan.getCantidadPartidosJugados());	
	}
}