package Taller6.modelo;

public class ProductoRepetidoException extends HamburguesaException{

	private static final long serialVersionUID = 1L;



	public ProductoRepetidoException(String producto) {
		super(producto);
		// TODO Auto-generated constructor stub
	}
	public String Nuevomensaje() {
		return " ---------------------------------------------------------------------------------------------------------------------------------------------------------------------\n"
				+ "EL PRODUCTO " + getProducto().toUpperCase() + " ESTA REPETIDO POR FAVOR VERIFIQUE EL ARCHIVO DE DATOS ANTES DE CORRER EL PROGRAMA \n ---------------------------------------------------------------------------------------------------------------------------------------------------------------------\n "
	; }
		
	
	
	
	
}
