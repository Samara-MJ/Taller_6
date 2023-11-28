package Taller6.modelo;

public class IngredienteRepetidoException extends HamburguesaException{

	public static final long serialVersionUID = 1L;
	

	public IngredienteRepetidoException(String producto) {
		super(producto);
		// TODO Auto-generated constructor stub
	}
	public String Nuevomensaje() {
		return " ---------------------------------------------------------------------------------------------------------------------------------------------------------------------\n"
				+ "EL INGREDIENTE " + getProducto().toUpperCase() + " ESTA REPETIDO POR FAVOR VERIFIQUE EL ARCHIVO DE DATOS ANTES DE CORRER EL PROGRAMA \n---------------------------------------------------------------------------------------------------------------------------------------------------------------------\n ";
		}
	
}
