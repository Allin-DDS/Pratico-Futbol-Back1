package ui.futbol5Desktop;

import java.awt.Color;

import org.uqbar.arena.aop.windows.TransactionalDialog;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;

import ui.futbol5ViewModels.Futbol5ViewModel;
import futbol5.Infraccion;
import futbol5.Jugador;

@SuppressWarnings("serial")
public class DatosDeJugadorView extends SimpleWindow<Jugador> {

	private Futbol5ViewModel ownerModel;
	
	public DatosDeJugadorView(WindowOwner owner, Jugador model) {
		super(owner, model);
		this.ownerModel = new Futbol5ViewModel();
	}
	@Override
	protected void createFormPanel(Panel mainPanel) {

		Panel panel = this.ownerModel.nuevoPanel(mainPanel, 2);
		
		this.ownerModel.crearLabelDeDatos("Nombre: ","nombre",panel);
		this.ownerModel.crearLabelDeDatos("Apodo: ","apodo",panel);
		this.ownerModel.crearLabelDeDatos("Hándicap: ","handicap",panel);
		this.ownerModel.crearLabelDeDatos("Edad: ","edad",panel);
		this.ownerModel.crearLabelDeDatos("Fecha de Nacimiento: ","fechaDeNacimiento", panel);
		this.ownerModel.crearLabelDeDatos("Promedio del último partido: ","promedioDeUltimoPartido",panel);
		this.ownerModel.crearLabelDeDatos("Promedio de todos los partidos que jugó: ","promedioDeTodosLosPartido",panel);
		this.ownerModel.crearLabelDeDatos("Cantidad de partidos que jugó: ","cantidadPartidosJugados",panel);

		Panel panelGrilla = this.ownerModel.nuevoPanel(mainPanel, 1);

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
		Table<Jugador> table = this.ownerModel.generarTablaJugador(panelGrilla);
		table.bindItemsToProperty("amigos");
	}
	@Override
	protected void addActions(Panel actionsPanel) {
		new Button(actionsPanel)//
		.setCaption("Volver")
		.onClick(()-> this.close());
		
	}

}
