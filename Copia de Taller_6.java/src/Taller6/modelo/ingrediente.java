package Taller6.modelo;

public class ingrediente {

	private String nombre;
	
	private int costoAdicional;
	
	public ingrediente(String nombre, int costoAdicional) {
		
		this.nombre = nombre;
		
		this.costoAdicional = costoAdicional;		
	}
	
	public int getCostoAdicional() {
		
		return costoAdicional;	
	}
	
	public String getNombre() {
		
		return nombre;
	}
	
	
}
