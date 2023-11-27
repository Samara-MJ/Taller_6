package Taller6.consola;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import Taller6.modelo.*;

import Taller6.procesamiento.Restaurante;

public class Aplicacion {

	
	public void mostrarMenu() 
	{
		System.out.println("\n1. Consultar el menu");
		System.out.println("2. Iniciar un pedido");
		System.out.println("3. Consultar pedidos con id");
		System.out.println("4. Cerrar aplicación");
	}
	
	public void ejecutarAplicacion() throws IOException
	{
		File archivoMenu = new File("data/menu.txt");
		archivoMenu.createNewFile();
		File archivoIngredientes = new File("data/ingredientes.txt");
		archivoIngredientes.createNewFile();
		File archivoCombos = new File("data/combos.txt");
		archivoCombos.createNewFile();
		Restaurante.cargarInformacionRestaurante(archivoIngredientes, archivoMenu, archivoCombos);
		System.out.println("===== Bienvenido a Hamburguesas El Corral =====");
		System.out.println("\n----- ¿Qué desea hacer? -----");
		
		boolean continuar = true;
		while (continuar)
		{
			try 
			{
				mostrarMenu();
				
				int opcion = Integer.parseInt(input("Por favor seleccione una opción"));
				if (opcion == 1) 
					ejecutarConsultarMenu();
				else if (opcion == 2) 
					ejecutarIniciarPedido();
				else if (opcion == 3)
					ejecutarConsultarPedido();
				else if (opcion == 4) {
					continuar = false;
					System.out.println("Gracias por su visita");
				}
				else 
				{
					System.out.println("Por favor seleccione una opción válida.");
				}
			}
			
			catch (NumberFormatException e)
			{
				System.out.println("Debe seleccionar uno de los números de las opciones.");
			}
		}
	}
	
	public String input(String mensaje)
	{
		try
		{
			System.out.print(mensaje + ": ");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			return reader.readLine();
		}
		catch (IOException e)
		{
			System.out.println("Error leyendo de la consola");
			e.printStackTrace();
		}
		return null;
	}
	
	public void ejecutarConsultarMenu() {
		ArrayList<productoMenu> menuBase = Restaurante.getMenuBase();
		ArrayList<ingrediente> menuIngredientes = Restaurante.getIngredientes();
		ArrayList<Combo> menuCombos = Restaurante.getCombos();
		
		System.out.println("\n=============== MENU CORRALERO ===============");
		System.out.println("\n----- Productos -----");
		for (productoMenu prod: menuBase) {
			String nom = prod.getNombre();
			int pre = prod.getPrecio();
			System.out.println(">" + nom + ": " + pre);
		}
		System.out.println("\n----- Combos -----");
		for (Combo comb: menuCombos) {
			String nom = comb.getNombre();
			int pre = comb.getPrecio();
			System.out.println(">" + nom + ": " + pre);
		}
		System.out.println("\n----- Adiciones -----");
		for (ingrediente ing: menuIngredientes) {
			String nom = ing.getNombre();
			int pre = ing.getCostoAdicional();
			System.out.println(">" + nom + ": " + pre);
		}
		
	}
	
	public void ejecutarIniciarPedido() throws IOException {
		System.out.println("\n-- Antes de iniciar denos unos datos --");
		String nombre = input("Nombre");
		String direccion = input("Dirección");
		Restaurante.iniciarPedido(nombre, direccion);
	}
	
	public void ejecutarConsultarPedido() {
		System.out.println("----- Consultar pedido -----");
		Integer id = Integer.parseInt(input("\nIngrese el id del Pedido"));
		String resp = Restaurante.consultarPedido(id);
		System.out.println(resp);
	}
	
	public static void main(String [] args) throws IOException {
		Aplicacion elCorral = new Aplicacion();
		elCorral.ejecutarAplicacion();
	}
	
}
