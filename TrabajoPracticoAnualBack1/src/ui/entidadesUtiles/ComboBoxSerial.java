package ui.entidadesUtiles;

import org.uqbar.commons.utils.Observable;
import org.uqbar.commons.utils.Transactional;

@Transactional
@Observable

public class ComboBoxSerial {
private String nombre;
private int serial;

public ComboBoxSerial(String nombre, int serial){
	this.nombre = nombre;
	this.serial = serial;
	
}
public String getNombre() {
	return nombre;
}
public void setNombre(String nombre) {
	this.nombre = nombre;
}
public int getSerial() {
	return serial;
}
public void setSerial(int serial) {
	this.serial = serial;
}
}
