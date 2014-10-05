package ui.futbol5Desktop;

import inscripcion.Inscripcion;
import ordenamiento.CriterioDeOrden;

import org.uqbar.arena.bindings.NotNullObservable;
import org.uqbar.arena.bindings.PropertyAdapter;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;
import org.uqbar.lacar.ui.model.ListBuilder;
import org.uqbar.lacar.ui.model.bindings.Binding;

import ui.futbol5ViewModels.Futbol5ViewModel;
import ui.futbol5ViewModels.GeneradorDeEquipoViewModel;
import dividirEquipos.CriterioParaDividirEquipos;


@SuppressWarnings("serial")
public class GeneradorDeEquipoView extends SimpleWindow<GeneradorDeEquipoViewModel> {
	
	private Futbol5ViewModel unModel;
	public GeneradorDeEquipoView(WindowOwner parent) {
		super(parent, new GeneradorDeEquipoViewModel());
		this.getModelObject().init();
		this.unModel = new Futbol5ViewModel();
		
	}
	@Override
	protected void createMainTemplate(Panel mainPanel) {
		this.setTitle("Generador de Equipos");
		this.setTaskDescription("Selecione los criterios que desee");
		
		super.createMainTemplate(mainPanel);

	}
	@Override
	protected void addActions(Panel actionsPanel) {
		new Button(actionsPanel)//
		.setCaption("Generar Equipos")
		.onClick(()-> this.getModelObject().generarEquiposTentativos());
		
		new Button(actionsPanel)//
		.setCaption("Ver datos del Jugador")
		.onClick(()-> new DatosDeJugadorView(this,this.getModelObject().getJugadorSeleccionado()).open());
		
		new Button(actionsPanel)//
		.setCaption("Confirmar Equipo")
		.onClick(()-> this.getModelObject().confirmarEquipos());
		
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		Panel SelectorCriterioFormPanel = this.unModel.nuevoPanel(mainPanel, 2);
		Panel SelectorOrdenFormPanel = this.unModel.nuevoPanel(mainPanel, 4);
	

		this.crearCriterioDeDivisionDeEquipo(SelectorCriterioFormPanel);	
		this.crearCriterioDeOrdenamiento(SelectorOrdenFormPanel);
		
		Panel equiposFormPanel = this.unModel.nuevoPanel(mainPanel,2);
	
	    new Label(equiposFormPanel).setText("Equipo N�1");
	    new Label(equiposFormPanel).setText("Equipo N�2");

	    this.crearGrillaDeEquipo("equipoNro1",equiposFormPanel);
	   // this.getModelObject().crear(mainPanel);
	    this.crearGrillaDeEquipo("equipoNro2",equiposFormPanel);
		
	}

	public Table<Inscripcion> crearGrillaDeEquipo(String string, Panel panel) {
		
		Table<Inscripcion> table = new Table<Inscripcion>(panel, Inscripcion.class);
		table.setHeigth(100);
		table.setWidth(150);

		table.bindItemsToProperty(string);
		table.bindValueToProperty("inscriptoSeleccionado");

		Column<Inscripcion> columnaJugador = new Column<Inscripcion>(table) //
		.setTitle("Jugadores del Equipo")
		.setFixedSize(150)
		.bindContentsToProperty("nombreJugador");
		
		new Column<Inscripcion>(table) //
		.setFixedSize(0)
		.bindBackground("jugador", new Transformadors())
		;
		
		

		return table;
		
		
	}
	
	
	private void crearCriterioDeOrdenamiento(Panel panel) {
		 new Label(panel).setText("Criterio de ordenamiento");
		   
			Selector<CriterioDeOrden> selectorDeOrden = new Selector<CriterioDeOrden>(panel) //
					.allowNull(false);
		    selectorDeOrden.bindValueToProperty("ordenamientoSeleccionado");

			Binding<ListBuilder<CriterioDeOrden>> itemsBindingDeOrden = selectorDeOrden.bindItemsToProperty("ordenamientosDisponibles");
			itemsBindingDeOrden.setAdapter(new PropertyAdapter(CriterioDeOrden.class, "nombre"));

			TextBox partidosAnteriores = new TextBox(panel);
			partidosAnteriores.bindValueToProperty("ultimosPartidosSeleccionados");	
			new Label(panel).setText("partidos anteriores");
			
			NotNullObservable elementSelected = new NotNullObservable("ordenamientoSeleccionado");
			partidosAnteriores.bindEnabled(elementSelected);
		
	}
	private void crearCriterioDeDivisionDeEquipo(Panel panel) {
new Label(panel).setText("Criterio de orden");
		
		Selector<CriterioParaDividirEquipos> selectorDeCriterio = new Selector<CriterioParaDividirEquipos>(panel) //
				.allowNull(false);
		selectorDeCriterio.bindValueToProperty("criterioSeleccionado");
			
		Binding<ListBuilder<CriterioParaDividirEquipos>> itemsBinding = selectorDeCriterio.bindItemsToProperty("criteriosDisponibles");
		itemsBinding.setAdapter(new PropertyAdapter(CriterioParaDividirEquipos.class, "nombre"));
		

		
	}

}
