package futbol5;

import org.uqbar.commons.utils.Observable;
import org.uqbar.commons.utils.Transactional;

@Transactional
@Observable
public class Infraccion {
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
