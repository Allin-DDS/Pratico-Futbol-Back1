package ui.futbol5Desktop;

import java.awt.Color;

import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.MainWindow;

import ui.futbol5ViewModels.GeneradorDeEquipoViewModel;




@SuppressWarnings("serial")
public class Futbol5View extends MainWindow<GeneradorDeEquipoViewModel> { 
	


	public Futbol5View(GeneradorDeEquipoViewModel model) {
		super(model);
	try{
		
		db.EntityManagerHelper.beginTransaction();
}
		catch(Exception ex){
		db.EntityManagerHelper.rollback();
		}
	}

	@Override
	public void createContents(Panel mainPanel) {
		 //Acá van todos label, textbox, etc
			setTitle("Bienvenidos a Fútbol 5");
		    mainPanel.setLayout(new VerticalLayout());
		    
		    new Label(mainPanel).setText("Aplicación de Escritorio de Fútbol 5")
		    .setBackground(Color.white)
		    .setForeground(Color.BLACK)
		    .setFontSize(16);	
		
		    
			Panel panelBusqueda = new Panel(mainPanel);
			panelBusqueda.setLayout(new ColumnLayout(2));
		
		new Label(panelBusqueda).setText("Clickear para generar los equipos")
		.setForeground(Color.blue);
		new Button(panelBusqueda)//
			.setCaption("Generar Equipos")
			.onClick(() -> new GeneradorDeEquipoView(this).open());
		
		new Label(panelBusqueda).setText("Clickear para buscar a determinados jugadores")
		.setForeground(Color.blue);
		new Button(panelBusqueda)
		.setCaption("Buscar Jugador")
		.onClick(() -> new BuscadorDeJugadorView(this).open());
		
		new Label(panelBusqueda).setText("Equipos previamente generado")
		.setForeground(Color.blue);
		new Button(panelBusqueda)
		.setCaption("Equipos generados")
		.onClick(() -> new EquiposGeneradoView(this).open());

	  }
	 public static void main(String[] args) {
		    new Futbol5View(new GeneradorDeEquipoViewModel()).startApplication();
		  }		

}