package ui.futbol5Desktop;

import model.futbol5.Jugador;

import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;

import ui.entidadesUtiles.AsistenteCreaciones;
import ui.futbol5ViewModels.EquiposGeneradoViewModel;

@SuppressWarnings("serial")
public class EquiposGeneradoView extends SimpleWindow<EquiposGeneradoViewModel> {

	private AsistenteCreaciones asistente;
	
	public EquiposGeneradoView(WindowOwner parent) {
		
		super(parent, new EquiposGeneradoViewModel());
		this.asistente = new AsistenteCreaciones();
		
	this.getModelObject().buscarEquiposGenerados();
	}

	@Override
	protected void addActions(Panel actionsPanel) {
		new Button(actionsPanel)//
		.setCaption("Volver")
		.onClick(()-> this.close());
		}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		
	Panel equiposFormPanel = this.asistente.nuevoPanel(mainPanel,2);
    new Label(equiposFormPanel).setText("Equipo Nº1");
    new Label(equiposFormPanel).setText("Equipo Nº2");

    Table<Jugador> table1 = new Table<Jugador>(equiposFormPanel, Jugador.class);
    new Column<Jugador>(table1) //
	.setTitle("Nombre")	
	.setFixedSize(75)
	.bindContentsToProperty("nombre");
    table1.bindItemsToProperty("equipo1");
    

    Table<Jugador> table2 = new Table<Jugador>(equiposFormPanel, Jugador.class);
    new Column<Jugador>(table2) //
	.setTitle("Nombre")	
	.setFixedSize(75)
	.bindContentsToProperty("nombre");
    table2.bindItemsToProperty("equipo2");

	
	

    
	}

}
