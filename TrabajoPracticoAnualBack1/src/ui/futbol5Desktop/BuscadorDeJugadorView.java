package ui.futbol5Desktop;

import java.awt.Color;









import org.uqbar.arena.bindings.NotNullObservable;
import org.uqbar.arena.bindings.PropertyAdapter;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;
import org.uqbar.lacar.ui.model.ListBuilder;
import org.uqbar.lacar.ui.model.bindings.Binding;

import ui.entidadesUtiles.ComboBoxBoolean;
import ui.entidadesUtiles.ComboBoxSerial;
import ui.futbol5ViewModels.BuscadorDeJugadorViewModel;
import ui.futbol5ViewModels.Futbol5ViewModel;
import futbol5.Jugador;

@SuppressWarnings("serial")
public class BuscadorDeJugadorView extends SimpleWindow<BuscadorDeJugadorViewModel> {


	private ui.futbol5ViewModels.Futbol5ViewModel unModel;

	public BuscadorDeJugadorView(WindowOwner parent) {
		super(parent, new BuscadorDeJugadorViewModel());
		unModel = new Futbol5ViewModel();
	}

	@Override
	protected void addActions(Panel actionsPanel) {
	
		  new Button(actionsPanel)//
		.setCaption("Buscar jugador")
		.disableOnError()
		.onClick(()-> this.getModelObject().buscar());
		  
		  new Button(actionsPanel)//
		.setCaption("Datos del Jugador")
		.disableOnError()
		.onClick(()-> new DatosDeJugadorView(this,this.getModelObject().getJugadorSeleccionado()).open());
			
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		
		Panel equiposFormPanel = this.unModel.nuevoPanel(mainPanel,2);
	
		
		
		this.busquedaGenerales(equiposFormPanel);
		this.crearFiltroInfracciones(equiposFormPanel);
		
		Panel rangoFormPanel = this.unModel.nuevoPanel(mainPanel,3);
		this.crearRangoHandicap(rangoFormPanel);
		this.crearRangoPromedio(rangoFormPanel);
		

		Panel grillaEquiposFormPanel = this.unModel.nuevoPanel(mainPanel,1);
		Table<Jugador> table = this.unModel.generarTablaJugador(grillaEquiposFormPanel);

		table.bindItemsToProperty("resultadoDeBusqueda");
		table.bindValueToProperty("jugadorSeleccionado");
	}

	private void crearFiltroInfracciones(Panel panel) {
		new Label(panel).setText("Filtro de infracciones").setForeground(Color.blue);
		
		 
		Selector<ComboBoxSerial> selectorDeInfraccion = new Selector<ComboBoxSerial>(panel) //
				.allowNull(false);
		selectorDeInfraccion.bindValueToProperty("infraccionElegidaCriterio");

		Binding<ListBuilder<ComboBoxSerial>> itemsBindingDeInfraccion = selectorDeInfraccion.bindItemsToProperty("filtroDeInfraccion");
		itemsBindingDeInfraccion.setAdapter(new PropertyAdapter(ComboBoxSerial.class, "nombre"));

		
	}

	private void crearRangoPromedio(Panel panel) {
		new Label(panel).setText("Filtro de promedio").setForeground(Color.blue);
		this.selectorDesdeHasta("promedioElegidoCriterio",panel,"promedio");
		
	}

	private void crearRangoHandicap(Panel panel) {
		new Label(panel).setText("Filtro de handicap").setForeground(Color.blue);
		

		 this.selectorDesdeHasta("handicapElegidoCriterio",panel,"handicap");
		
	}

	private void selectorDesdeHasta(String string, Panel panel, String stringBind) {
		
		TextBox txtbox =  new TextBox(panel);
		txtbox.bindValueToProperty(stringBind);
		
		 Selector<ComboBoxBoolean> selector = new Selector<ComboBoxBoolean>(panel) //
					.allowNull(false);
		 selector.bindValueToProperty(string);

			Binding<ListBuilder<ComboBoxBoolean>> itemsBinding = selector.bindItemsToProperty("filtrosDesdeHasta");
			itemsBinding.setAdapter(new PropertyAdapter(ComboBoxBoolean.class, "nombre"));
			
			NotNullObservable elementSelected = new NotNullObservable(string);
			txtbox.bindEnabled(elementSelected);
		
	}

	private void busquedaGenerales(Panel equiposFormPanel) {
		this.unModel.crearLabelDeDatosAIngresar("Nombre: ","nombre",equiposFormPanel);
		this.unModel.crearLabelDeDatosAIngresar("Apodo: ","apodo",equiposFormPanel);
		this.unModel.crearLabelDeDatosAIngresar("Fecha: ","fecha",equiposFormPanel);
		


	}
	
}
