package Taller6.modelo;

public class productoMenu implements Producto {

	private String nombre;
	
	private int precioBase;
	
	public productoMenu(String nombre, int precioBase) {
		
		this.nombre = nombre;
				
		this.precioBase = precioBase;			
	}
	
	public String getNombre() {
		
		return nombre;
	}
	
	public int getPrecio() {
		
		return precioBase;
	}
	
	public String generarTextoFactura() {
		return "1 " + this.getNombre() + " ... " + Integer.toString(this.getPrecio()); 
	}
	
	
}
