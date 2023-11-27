package Taller6.modelo;

import java.util.*;

public class Combo implements Producto {

	private double descuento;
	
	private String nombreCombo;
	
	private ArrayList<productoMenu> Items = new ArrayList<productoMenu>();
	
	private int precio;
	
	public Combo(String nombre, double descuento) {
		
		this.nombreCombo = nombre;
		
		this.setDescuento(descuento);
	}
	
	public String getNombre() {
		
		return nombreCombo;
	}
	
	public int getPrecio() {
		return precio;
	}
	
	public void setPrecio(int n) {
		this.precio = n;
	}
	
	public void agregarItemACombo(productoMenu Combo) {
		Items.add(Combo);
		
	}

	public double getDescuento() {
		return descuento;
	}

	public void setDescuento(double descuento) {
		this.descuento = descuento;
	}
	
	public String generarTextoFactura()
	{
		String text = "1 " + (this.getNombre()) + "(";
		for (productoMenu item: Items) {
			
			text += item.getNombre() + ",";
		}
		text += "+";
		text = text.replace(",+", "");
		text += ") ... " + Integer.toString(this.getPrecio());
		
		return text;
	}
	 
	
	
	
	
	
}
