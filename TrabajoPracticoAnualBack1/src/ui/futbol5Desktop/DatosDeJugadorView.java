package ui.futbol5Desktop;

import java.awt.Color;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;

import ui.entidadesUtiles.AsistenteCreaciones;
import model.futbol5.Infraccion;
import model.futbol5.Jugador;

@SuppressWarnings("serial")
public class DatosDeJugadorView extends SimpleWindow<Jugador> {

	private AsistenteCreaciones asistente;
	
	public DatosDeJugadorView(WindowOwner owner, Jugador model) {
		super(owner, model);
		this.asistente = new AsistenteCreaciones();
	}
	@Override
	protected void createFormPanel(Panel mainPanel) {

		Panel panel = this.asistente.nuevoPanel(mainPanel, 2);
		
		this.asistente.crearLabelDeDatos("Nombre: ","nombre",panel);
		this.asistente.crearLabelDeDatos("Apodo: ","apodo",panel);
		this.asistente.crearLabelDeDatos("Hándicap: ","handicap",panel);
		this.asistente.crearLabelDeDatos("Edad: ","edad",panel);
		this.asistente.crearLabelDeDatos("Fecha de Nacimiento: ","fechaDeNacimiento", panel);
		this.asistente.crearLabelDeDatos("Promedio del último partido: ","promedioDeUltimoPartido",panel);
		this.asistente.crearLabelDeDatos("Promedio de todos los partidos que jugó: ","promedioDeTodosLosPartido",panel);
		this.asistente.crearLabelDeDatos("Cantidad de partidos que jugó: ","cantidadPartidosJugados",panel);

		Panel panelGrilla = this.asistente.nuevoPanel(mainPanel, 1);

		this.crearGrillaAmigos(panelGrilla);
		this.crearGrillaInfracciones(panelGrilla);
		
	}

	private void crearGrillaInfracciones(Panel panelGrilla) {
		 new Label(panelGrilla).setText("Infracciones").setForeground(Color.blue);
		 
		Table<Infraccion> table = new Table<Infraccion>(panelGrilla, Infraccion.class);
		table.setHeigth(50);
		table.setWidth(300);		
		table.bindItemsToProperty("infracciones");
		
		new Column<Infraccion>(table) //
		.setTitle("Motivo")
		.setFixedSize(100)
		.bindContentsToProperty("motivo");
				
		new Column<Infraccion>(table) //
		.setTitle("Fecha")
		.setFixedSize(100)
		.bindContentsToProperty("fechaPartido");
		
		new Column<Infraccion>(table) //
		.setTitle("Hora")
		.setFixedSize(100)
		.bindContentsToProperty("horaPartido");
	}

	private void crearGrillaAmigos(Panel panelGrilla) {
		
		new Label(panelGrilla).setText("Amigos").setForeground(Color.blue);
		Table<Jugador> table = this.asistente.generarTablaJugador(panelGrilla);
		table.bindItemsToProperty("amigos");
	}
	@Override
	protected void addActions(Panel actionsPanel) {
		new Button(actionsPanel)//
		.setCaption("Volver")
		.onClick(()-> this.close());
		
	}

}
