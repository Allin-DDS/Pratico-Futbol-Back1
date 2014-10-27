package ui.entidadesUtiles;

import java.awt.Color;

import model.futbol5.Jugador;

import com.uqbar.commons.collections.Transformer;

public class Transformadors implements Transformer<Jugador, Color> {



	@Override
	public Color transform(Jugador inscripto) {
		
		if(inscripto.getHandicap() > 8){
			return Color.blue;
			
		}
		return Color.white;
	}

}
