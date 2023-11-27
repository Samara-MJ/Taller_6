package Taller6.modelo;

public abstract class HamburguesaException extends Exception {

	private static final long serialVersionUID = 1L;
	
	protected String producto;

	public String getProducto() {
		return producto;
	}

	public HamburguesaException(String producto) {
		this.producto = producto;
	}
	
	public String Nuevomensaje() {
		return null;
		
	}
	
}
