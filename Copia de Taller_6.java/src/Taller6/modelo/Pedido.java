package Taller6.modelo;

import java.io.*;
import java.util.*;

public class Pedido{

	static int numeroPedidos = 0;
	private int idPedido;
	private String nombreCliente;
	private String direccionCliente;
	private int precioNeto = 0;
	private ArrayList<Producto> productos = new ArrayList<Producto>();
	private String Factura;
	public Pedido(String nombreCliente, String direccionCliente) throws IOException
	{
		this.nombreCliente = nombreCliente;
		this.direccionCliente = direccionCliente;
		numeroPedidos += 1;
	}
	
	public int getIdPedido() {
		return idPedido;
	}

	public void agregarProducto(Producto item) {
		this.productos.add(item);
		this.precioNeto += item.getPrecio();
	}
	
	private int getPrecioNetoPedido() {
		return precioNeto;
	}
	
	private int getPrecioIVAPedido() {
		int iva = (int)(this.getPrecioNetoPedido()*0.19);
		return iva;
	}
	
	private int getPrecioTotalPedido() {
		return this.getPrecioNetoPedido() + this.getPrecioIVAPedido();
	}
	
	public void setFactura(String factura) {
		this.Factura = factura;
	}
	
	public String getFactura() {
		return this.Factura;
	}
	
	public void setId(int id) {
		this.idPedido = id;
	}
	
	
	private String generarTextoFactura() {
		String factura = "\nIdPedido: " + this.idPedido;
		factura += "\nNombre: " + this.nombreCliente;
		factura += "\nDirección: " + this.direccionCliente + "\n";
		factura += "===========================\n";
		for (Producto prod: this.productos) {
			factura += prod.generarTextoFactura() + "\n";
		}
		factura += "===========================";
		factura += "\nNúmero: " + numeroPedidos;
		factura += "\nBase: " + this.getPrecioNetoPedido() + "   IVA: " + this.getPrecioIVAPedido();
		factura += "\n     Precio total: " + this.getPrecioTotalPedido();
		this.setFactura(factura);
		System.out.println("\n" + factura + "\n     ¡¡¡GRACIAS POR TU COMPRA!!!     ");
		return factura;
	}
	
	public void guardarFactura() throws IOException
	{
		BufferedWriter facturas = new BufferedWriter(new FileWriter("data\\Facturas", true));
		String textoFactura = this.generarTextoFactura();
		facturas.write(textoFactura);
		facturas.newLine();
		facturas.close();
	}
	
}
