package ui.futbol5Desktop;

import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
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
		}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		
	Panel equiposFormPanel = this.asistente.nuevoPanel(mainPanel,2);
    new Label(equiposFormPanel).setText("Equipo Nº1");
    new Label(equiposFormPanel).setText("Equipo Nº2");

    Table<String> table1 = new Table<String>(equiposFormPanel, String.class);
    table1.bindItemsToProperty("equipo1");
    

	new Table<String>(equiposFormPanel, String.class)
		.bindItemsToProperty("equipo2");

	

    
	}

}
