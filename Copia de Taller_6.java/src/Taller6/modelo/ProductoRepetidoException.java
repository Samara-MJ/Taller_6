package Taller6.modelo;

public class ProductoRepetidoException extends HamburguesaException{

	private static final long serialVersionUID = 1L;



	public ProductoRepetidoException(String producto) {
		super(producto);
		// TODO Auto-generated constructor stub
	}
	public String Nuevomensaje() {
		return "EL PRODUCTO"+ getProducto() + "ESTA REPETIDO POR FAVOR VERIFIQUE EL ARCHIVO DE DATOS  Y EJECUTE EL PROGRAMA DE NUEVO\"";
		
	}
	
	
	
}
