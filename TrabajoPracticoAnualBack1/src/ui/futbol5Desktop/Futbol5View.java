package ui.futbol5Desktop;

import java.awt.Color;

import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.MainWindow;

import ui.futbol5ViewModels.Futbol5ViewModel;



public class Futbol5View extends MainWindow<Futbol5ViewModel>{ 
	
	public Futbol5View() {
		super(new Futbol5ViewModel());
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
		
		Panel panelBusqueda = this.getModelObject().nuevoPanel(mainPanel, 2);
		
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

	  }
	 public static void main(String[] args) {
		    new Futbol5View().startApplication();
		  }		

}