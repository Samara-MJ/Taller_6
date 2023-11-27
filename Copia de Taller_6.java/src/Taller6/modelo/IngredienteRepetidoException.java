package Taller6.modelo;

public class IngredienteRepetidoException extends HamburguesaException{

	public static final long serialVersionUID = 1L;
	

	public IngredienteRepetidoException(String producto) {
		super(producto);
		// TODO Auto-generated constructor stub
	}
	public String Nuevomensaje() {
		return "EL INGREDIENTE" + getProducto() + "ESTA REPETIDO POR FAVOR VERIFIQUE EL ARCHIVO DE DATOS  Y EJECUTE EL PROGRAMA DE NUEVO";
		}
	
}
