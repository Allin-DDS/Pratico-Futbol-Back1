package ui.futbol5ViewModels;

import static db.EntityManagerHelper.createQuery;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

public class EquiposGeneradoViewModel {

	private List<String> equipo1;
	private List<String> equipo2;

	

	public void buscarEquiposGenerados() {

	this.setEquipo1(new ArrayList<String>());
	
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

		this.setEquipo1(equipo1Query.getResultList());
		this.setEquipo2(equipo2Query.getResultList());
		
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
	public List<String> getEquipo1() {
		return equipo1;
	}



	/**
	 * @param equipo1 the equipo1 to set
	 */
	public void setEquipo1(List<String> equipo1) {
		this.equipo1 = equipo1;
	}



	/**
	 * @return the equipo2
	 */
	public List<String> getEquipo2() {
		return equipo2;
	}



	/**
	 * @param equipo2 the equipo2 to set
	 */
	public void setEquipo2(List<String> equipo2) {
		this.equipo2 = equipo2;
	}

}
