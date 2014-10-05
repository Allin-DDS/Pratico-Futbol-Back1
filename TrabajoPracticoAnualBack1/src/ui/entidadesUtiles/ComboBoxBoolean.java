package ui.entidadesUtiles;

import org.uqbar.commons.utils.Observable;
import org.uqbar.commons.utils.Transactional;

@Transactional
@Observable

public class ComboBoxBoolean {
private String nombre;
private boolean booleano;

public ComboBoxBoolean(String nombre, boolean i){
	this.nombre = nombre;
	this.booleano = i;
}
public String getNombre() {
	return nombre;
}
public void setNombre(String nombre) {
	this.nombre = nombre;
}
public boolean isBooleano() {
	return booleano;
}
public void setBooleano(boolean booleano) {
	this.booleano = booleano;
}

}
