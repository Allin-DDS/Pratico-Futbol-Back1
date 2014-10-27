package model.futbol5;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.uqbar.commons.utils.Observable;
import org.uqbar.commons.utils.Transactional;

@Transactional
@Observable
@Entity
@Table(name = "Infracciones")
public class Infraccion extends PersistentEntity{
	
	@ManyToOne
	private Partido partido;
	private String motivo;

	public Infraccion(Partido partido,String motivo){
		this.partido = partido;
		this.motivo = motivo;
		
	}

	public String getFechaPartido() {
		return partido.getFecha().toString();
	}

	public String getHoraPartido() {

		return partido.getHorario().toString();
	}

	public String getMotivo() {
		return motivo;
	}
}
