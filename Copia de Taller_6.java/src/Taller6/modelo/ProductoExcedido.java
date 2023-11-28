package Taller6.modelo;

public class ProductoExcedido extends Exception {

	private static final long serialVersionUID = 1L;
	private int valor;
	
	public int getValor() {
		return valor;
	}
	public ProductoExcedido(int valor) {
		super();
		this.valor = valor;
	}
	public String Nuevomensaje() {
		return "------------------------------------------------------------------------------------------\n"
				+ "EL PRECIO MAXIMO DE UN PEDIDO FUE ALCANZADO, POR FAVOR ELMINE UN PRODUCTO\n------------------------------------------------------------------------------------------\n";
	}
	

}
