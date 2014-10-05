package ui.futbol5ViewModels;

import java.awt.Color;

import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.commons.utils.Observable;

import ui.futbol5Desktop.Transformadors;
import futbol5.Jugador;

@Observable
public class Futbol5ViewModel {

	public void crearLabelDeDatos(String string, String string2, Panel panel) {
		 new Label(panel).setText(string).setForeground(Color.blue);
		 new Label(panel).bindValueToProperty(string2);
		
	}
	public void crearLabelDeDatosAIngresar(String string, String string2, Panel panel) {
		 new Label(panel).setText(string).setForeground(Color.blue);
		 new TextBox(panel).bindValueToProperty(string2);
		
	}
	public Panel nuevoPanel(Panel mainPanel, int i) {
		Panel panel = new Panel(mainPanel);
		panel.setLayout(new ColumnLayout(i));
		return panel;
	}

	public Table<Jugador> generarTablaJugador(Panel panelGrilla) {
		
		Table<Jugador> table = new Table<Jugador>(panelGrilla, Jugador.class);
		table.setHeigth(80);
		table.setWidth(300);
		
		new Column<Jugador>(table) //
		.setTitle("Nombre")	
		.setFixedSize(75)
		.bindContentsToProperty("nombre");

		new Column<Jugador>(table) //
		.setTitle("Apodo")
		.setFixedSize(75)
		.bindContentsToProperty("apodo");
		
		new Column<Jugador>(table) //
		.setTitle("Handicap")
		.setFixedSize(75)
		.bindContentsToProperty("handicap");
		
		new Column<Jugador>(table) //
		.setTitle("Promedio")
		.setFixedSize(75)
		.bindContentsToProperty("promedioDeTodosLosPartido");
		
		new Column<Jugador>(table)
		.setFixedSize(0)
		.bindBackground("jugador", new Transformadors());
		
		return table;
	}

}
