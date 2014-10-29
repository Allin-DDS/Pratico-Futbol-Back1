package ui.futbol5ViewModels;

import static db.EntityManagerHelper.createQuery;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import model.futbol5.Jugador;

public class EquiposGeneradoViewModel {

	private List<Jugador> equipo1;
	private List<Jugador> equipo2;

	

	public void buscarEquiposGenerados() {

		
		db.EntityManagerHelper.beginTransaction();
		db.EntityManagerHelper.getEntityManager();
		String query1 = "select j.nombre FROM Jugador AS j, Inscripcion AS i " +
		"WHERE j.id = i.jugador "
		+"AND i.numeroDeEquipo = '1' "
		+"ORDER BY i.id DESC"
		;

		String query2 = "select j.nombre FROM Jugador AS j, Inscripcion AS i " +
				"WHERE j.id = i.jugador "
				+"AND i.numeroDeEquipo = '2' "
				+"ORDER BY i.id DESC"
				;
		Query equipo1Query = createQuery(query1);
		equipo1Query.setMaxResults(5);
		Query equipo2Query  = createQuery(query2);
		equipo2Query.setMaxResults(5);

		List<String> listaEquipo1 = equipo1Query.getResultList();
		List<String> listaEquipo2 = equipo2Query.getResultList();
		List<Jugador> listaJugadorE1 = new ArrayList<Jugador>(); 
		List<Jugador> listaJugadorE2 = new ArrayList<Jugador>();
		
		for(String jugadorNombre : listaEquipo1){
			Jugador j = new Jugador();
			j.setNombre(jugadorNombre);
			listaJugadorE1.add(j);
		}
		for(String jugadorNombre : listaEquipo2){
			Jugador j = new Jugador();
			j.setNombre(jugadorNombre);
			listaJugadorE2.add(j);
		}
		this.equipo1 = listaJugadorE1;
		this.equipo2 = listaJugadorE2;
		
		
		
		
		/*
		  String updateNumerosEquipos = "UPDATE Inscripcion i SET i.numeroDeEquipo = 0 ";
		 
		Query updateQuery = createQuery(updateNumerosEquipos);
		int i = updateQuery.executeUpdate();
		 */		
		db.EntityManagerHelper.closeEntityManager();
		}



	/**
	 * @return the equipo1
	 */
	public List<Jugador> getEquipo1() {
		return equipo1;
	}



	/**
	 * @param equipo1 the equipo1 to set
	 */
	public void setEquipo1(List<Jugador> equipo1) {
		this.equipo1 = equipo1;
	}



	/**
	 * @return the equipo2
	 */
	public List<Jugador> getEquipo2() {
		return equipo2;
	}



	/**
	 * @param equipo2 the equipo2 to set
	 */
	public void setEquipo2(List<Jugador> equipo2) {
		this.equipo2 = equipo2;
	}

}
