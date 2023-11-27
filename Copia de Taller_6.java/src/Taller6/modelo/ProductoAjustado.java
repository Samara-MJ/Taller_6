package Taller6.modelo;

import java.util.*;

public class ProductoAjustado implements Producto {

	private productoMenu Base;
	private int precioAjustado = 0;
	private ArrayList<ingrediente> Agregados = new ArrayList<ingrediente>();
	private ArrayList<ingrediente> Eliminados = new ArrayList<ingrediente>();
	
	public ProductoAjustado(productoMenu Base, int precioAjustado) {
		this.Base = Base;
		this.precioAjustado += precioAjustado;
		
	}
	
	public int getPrecio() {
		return precioAjustado;
	}
	
	public void addPrecio(int extra) {
		this.precioAjustado += extra;
	}
	
	public String getNombre() {
		return Base.getNombre();
	}
	
	public void agregarIngrediente(ingrediente adicion) {
		Agregados.add(adicion);
		this.addPrecio(adicion.getCostoAdicional());
	}
	
	public void eliminarIngrediente(ingrediente restar)
	{
		Eliminados.add(restar);
	}
	
	public String generarTextoFactura()
	{
		String text = "1 " + this.getNombre();
		if (this.Agregados.size() > 0)
		{
			text += " con ";
			for (ingrediente agregar: this.Agregados){
				text += agregar.getNombre() + ","; 
			}
			text += ",";
		}
		if (this.Eliminados.size() > 0)
		{
			text += " sin ";
			for (ingrediente restar: this.Eliminados)
			{
				text += restar.getNombre() + ",";
			}
			text += "+";
			text = text.replace(",,", " y");
			text = text.replace(",+", "");
		}
		else
		{
			text = text.replace(",,", "");
		}
		
		text += " ... " + this.getPrecio();
		
		return text;
	}
}
