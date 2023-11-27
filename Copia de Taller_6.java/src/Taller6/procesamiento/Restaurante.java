package Taller6.procesamiento;

import java.io.*;

import java.util.*;

import Taller6.modelo.Combo;
import Taller6.modelo.Pedido;
import Taller6.modelo.ingrediente;
import Taller6.modelo.productoMenu;
import Taller6.modelo.*;


public class Restaurante {
	
	static ArrayList<ingrediente> Ingredientes = new ArrayList<>();
	static ArrayList<productoMenu> Productos = new ArrayList<>();
	static ArrayList<Combo> Combos = new ArrayList<>();
	static Map<Integer, Pedido> Pedidos = new HashMap<Integer,Pedido>();
	
	public static ArrayList<productoMenu> getMenuBase()
	{
		return Productos;
	}
	
	public static ArrayList<ingrediente> getIngredientes()
	{
		return Ingredientes;
	}
	
	public static ArrayList<Combo> getCombos()
	{
		return Combos;
	}
	
	public static void cargarInformacionRestaurante(File archivoIngredientes, File archivoMenu, File archivoCombos) throws FileNotFoundException, IOException
	{
		try {
			
		cargarIngredientes(archivoIngredientes);
		cargarMenu(archivoMenu);
		cargarCombos(archivoCombos);
		}
		catch(IngredienteRepetidoException e){ 
			System.out.println(e.Nuevomensaje());
			
		}catch (ProductoRepetidoException e){
			System.out.println(e.Nuevomensaje());
			
		}
		
	}

	private static void cargarIngredientes(File archivoIngredientes) throws IOException, IngredienteRepetidoException
	{
		try {
		BufferedReader br = new BufferedReader(new FileReader(archivoIngredientes));
		String linea = br.readLine();
		
		while (linea != null)
		{
			String[] partes = linea.split(";");
			String nombre = partes[0];
			int costoAdicional = Integer.parseInt(partes[1]);
			ingrediente ing = new ingrediente(nombre, costoAdicional);
			Ingredientes.add(ing);
			if(verificaring(nombre, Ingredientes)== true) {
				
				throw new IngredienteRepetidoException(nombre);
				
			}
			else {
			linea = br.readLine();
		}
		br.close();
		}}
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private static void cargarMenu(File archivoMenu) throws IOException, ProductoRepetidoException
	{
		
		try {
		BufferedReader br = new BufferedReader(new FileReader(archivoMenu));
		String linea = br.readLine();
		
		while (linea != null)
		{
			String[] partes = linea.split(";");
			String nombre = partes[0];
			int precioBase = Integer.parseInt(partes[1]);
			productoMenu pr = new productoMenu(nombre, precioBase);
			Productos.add(pr);
			
			if(verificarprod(nombre, Productos)== true) {
				
				throw new ProductoRepetidoException(nombre);
				
			}
			else {
			linea = br.readLine();
		}
			
			
		br.close();
		}}
		catch (IOException e){
			e.printStackTrace();
		}
		
	}
	
	private static void cargarCombos(File archivoCombos)
	{
		try {
		BufferedReader br = new BufferedReader(new FileReader(archivoCombos));
		String linea = br.readLine();
		
		while (linea != null)
		{
			String[] partes = linea.split(";");
			String[] part = new String[]{partes[2], partes[3], partes[4]};
			String nombre = partes[0];
			double descuento = porcentaje(partes[1]);
			Combo pr = new Combo(nombre, descuento);
			int precio = 0;
			for (String comp: part)
			{
				for (productoMenu prod : Productos) {
						
					String nom = prod.getNombre();
					if (nom.equals(comp))
					{
						int precioadd = prod.getPrecio();
						precio += ((int)(precioadd - precioadd*descuento));
						pr.agregarItemACombo(prod);
					}
					}
			}
			pr.setPrecio(precio);
			Combos.add(pr);
			linea = br.readLine();
		}
		br.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static double porcentaje(String n)
	{
		double r = Double.parseDouble(n.replace("%", "")) / 100;
		return r;
	}
	
	public static String input(String mensaje)
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
	
	public static String consultarPedido(Integer id) {
			Pedido order = Pedidos.get(id);
			if (order != null){
				return order.getFactura();
			}
			else {
				return ("No existe ningún pedido con el id " + id);
			}
		}
	
	public static boolean verificaring(String nombre, ArrayList<ingrediente> ingrediente) {
		
		boolean ing = false;
		for (ingrediente i: ingrediente) {
			if(i.getNombre().equals(nombre)) {
				ing = true;
				break;
			}
		}
		
			return ing;
			
		
		
	}
	
	public static boolean verificarprod(String nombre, ArrayList<productoMenu> productoMenu) {
		
		boolean pr = false;
		for (productoMenu p: productoMenu) {
			if(p.getNombre().equals(nombre)) {
				pr = true;
				break;
			}
		}
		
			return pr;
			
		
		
	}
	
	public static void iniciarPedido(String nombreCliente, String direccionCliente) throws IOException{
		Pedido pedido = new Pedido(nombreCliente, direccionCliente);
		boolean continuarPedido = true; 
		while (continuarPedido) {
			System.out.println("\n----- Escoja un tipo de producto -----");
			System.out.println("\n1 - Combos");
			System.out.println("2 - Menu base");
			System.out.println("\n3 - Terminar pedido");
			int r1 = Integer.parseInt(input(""));
			if (r1 == 1) {
				System.out.println("\n----- Combos -----");
				int i1 = 1;
				for (Combo comb: Restaurante.getCombos()) {
					String nom = comb.getNombre();
					int pre = comb.getPrecio();
					System.out.println(i1 + " - " + nom + ": " + pre);
					i1 ++;
				}
				int rCombos = Integer.parseInt(input(""));
				String nCombos = null;
				if (rCombos == 1) {
					nCombos = "combo corral";
				}
				else if (rCombos == 2) {
					nCombos = "combo corral queso";
				}
				else if (rCombos == 3) {
					nCombos = "combo todoterreno";
				}
				else if (rCombos == 4) {
					nCombos = "combo especial";
				}
				boolean b1 = false;
				for (int ic = 0; b1 != true; ic++) {
					Combo comb = Restaurante.getCombos().get(ic);
					if (comb.getNombre().equals(nCombos)){
						pedido.agregarProducto(comb);
						b1 = true;
					}
				}
			}
			else if (r1 == 2) {
				int i1 = 1;
				for (productoMenu prod: Restaurante.getMenuBase()) {
					String nom = prod.getNombre();
					int pre = prod.getPrecio();
					System.out.println(i1 + " - " + nom + ": " + pre);
					i1 ++;
				}
				int rMenu = Integer.parseInt(input(""));
				String nMenu = null;
				if (rMenu == 1) {
					nMenu = "corral";
				}
				else if (rMenu == 2) {
					nMenu = "corral queso";
				}
				else if (rMenu == 3) {
					nMenu = "corral pollo";
				}
				else if (rMenu == 4) {
					nMenu = "corralita";
				}
				else if (rMenu == 5) {
					nMenu = "todoterreno";
				}
				else if (rMenu == 6) {
					nMenu = "1/2 libra";
				}
				else if (rMenu == 7) {
					nMenu = "especial";
				}
				else if (rMenu == 8) {
					nMenu = "casera";
				}
				else if (rMenu == 9) {
					nMenu = "mexicana";
				}
				else if (rMenu == 10) {
					nMenu = "criolla";
				}
				else if (rMenu == 11) {
					nMenu = "costeña";
				}
				else if (rMenu == 12) {
					nMenu = "hawaiana";
				}
				else if (rMenu == 13) {
					nMenu = "wrap de pollo";
				}
				else if (rMenu == 14) {
					nMenu = "wrap de lomo";
				}
				else if (rMenu == 15) {
					nMenu = "ensalada mexicana";
				}
				else if (rMenu == 16) {
					nMenu = "papas medianas";
				}
				else if (rMenu == 17) {
					nMenu = "papas grandes";
				}
				else if (rMenu == 18) {
					nMenu = "papas en casco medianas";
				}
				else if (rMenu == 19) {
					nMenu = "papas en casco grande";
				}
				else if (rMenu == 20) {
					nMenu = "agua cristal sin gas";
				}
				else if (rMenu == 21) {
					nMenu = "agua cristal con gas";
				}
				else if (rMenu == 22) {
					nMenu = "gaseosa";
				}
				String r2 = input("¿Desea adicionar o eliminar algún ingrediente? (Si/No)").toLowerCase();
				productoMenu menu = null; 
				boolean b1 = false;
				for (int ic = 0; b1 != true; ic++) {
					productoMenu base = Restaurante.getMenuBase().get(ic);
					if (base.getNombre().equals(nMenu)){
						menu = base;
						b1 = true;
					}
				}
				if (r2.equals("no")) {
						pedido.agregarProducto(menu);
					}
				else if (r2.equals("si")) {
					ProductoAjustado ajuste = new ProductoAjustado(menu, menu.getPrecio());
					boolean bIngredientes = true;
					while (bIngredientes) {
					System.out.println("\n----- Acciones -----");
					System.out.println("1 - Adicionar ingrediente");
					System.out.println("2 - Eliminar ingrediente");
					int r3 = Integer.parseInt(input("Elija una acción"));
					System.out.println("\n----- Ingredientes -----");
					int i2 = 1;
					for (ingrediente ing: Restaurante.getIngredientes()) {
						String nom = ing.getNombre();
						int pre = ing.getCostoAdicional();
						System.out.println(i2 + " - " + nom + ": " + pre);
						i2 ++;
					}
					int rIngrediente = Integer.parseInt(input("Elija un ingrediente"));
					String nIngrediente = null;
					if (rIngrediente == 1) {
						nIngrediente = "lechuga";
					}
					else if (rIngrediente == 2) {
						nIngrediente = "tomate";
					}
					else if (rIngrediente == 3) {
						nIngrediente = "cebolla";
					}
					else if (rIngrediente == 4) {
						nIngrediente = "queso mozzarella";
					}
					else if (rIngrediente == 5) {
						nIngrediente = "huevo";
					}
					else if (rIngrediente == 6) {
						nIngrediente = "queso americano";
					}
					else if (rIngrediente == 7) {
						nIngrediente = "tocineta express";
					}
					else if (rIngrediente == 8) {
						nIngrediente = "papa callejera";
					}
					else if (rIngrediente == 9) {
						nIngrediente = "pepinillos";
					}
					else if (rIngrediente == 10) {
						nIngrediente = "cebolla grille";
					}
					else if (rIngrediente == 11) {
						nIngrediente = "suero costeño";
					}
					else if (rIngrediente == 12) {
						nIngrediente = "frijol refrito";
					}
					else if (rIngrediente == 13) {
						nIngrediente = "queso fundido";
					}
					else if (rIngrediente == 14) {
						nIngrediente = "tocineta picada";
					}
					else if (rIngrediente == 15) {
						nIngrediente = "piña";
					}
					boolean b3 = false;
					ingrediente extra = null;
					for (int ic = 0; b3 != true; ic++) {
						ingrediente ingr = Restaurante.getIngredientes().get(ic);
						if (ingr.getNombre().equals(nIngrediente)){
							extra = ingr;
							b3 = true;
						}
						}
					if (r3 == 1) {
						ajuste.agregarIngrediente(extra);
					}
					else if (r3 == 2){
						ajuste.eliminarIngrediente(extra);
					}
					System.out.println("¿Desea seguir modificando el producto? (Si/No)");
					String r4 = input("").toLowerCase();
					if (r4.equals("no")) {
						pedido.agregarProducto(ajuste);
						bIngredientes = false;
					}
					else if (r4.equals("si")) {
						continue;
					}
				}
				}
			}
			if (r1 == 3) {
				Random rand = new Random();
				Integer id = rand.nextInt(1000000);
				if (Pedidos.containsKey(id) == false) {
					pedido.setId(id);
					pedido.guardarFactura();
					Pedidos.put(pedido.getIdPedido(), pedido);
					continuarPedido = false;
				}
			}
		}
	}
	
	
	
}
