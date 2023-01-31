package edu.java.cuentaCorriente;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Clase de acceso a la aplicación de gestión de cuentas
 * @author garfe
 */
public class Principal {

	public static void main(String[] args) {
		
		//Inicializar el tipo cuenta
		CuentaCorriente ccc = new CuentaCorriente();
		//Listado de cuentas (BD)
		List<CuentaCorriente> listaCcc = new ArrayList<>();
		
		// Imprimir el menú por consola
		// Scanner con la opción del usuario
		Scanner entradaOpcion = new Scanner(System.in);		
		Boolean cerrarMenu = false;
		int opcion;
		while(!cerrarMenu) {	
			//Mostramos el menú
			mostrarMenu();
			System.out.println("Introduza la opción deseada: ");
			opcion = entradaOpcion.nextInt();
			System.out.println("[INFO] - Has seleccionado la opcion " + opcion);
			
			switch (opcion) {			
				case 1:
					listaCcc.add(ccc.crearCuenta(listaCcc));					
					System.out.println("Cuenta creada: " + listaCcc.get(listaCcc.size()-1));
					break;
				case 2:
					//llamamos al método
					listaCcc = ccc.ingresoCuenta(listaCcc);
					break;
				case 3:
					listaCcc=ccc.sacarDinero(listaCcc);
					break;
				case 4:
					ccc.mostrarCuentasUsuario(listaCcc);
					break;
				case 5:
					System.out.println("Desconectando, gracias por su confianza");
					cerrarMenu = true;
					break;
				default:					
					System.err.println("** Error opción "+opcion+" no disponible **");
					System.out.println("Introduzca la opción deseada [1..5]: ");
					opcion = entradaOpcion.nextInt();
					System.out.println("[INFO] - Has seleccionado la opcion " + opcion);
			}
		}		

	}
	static void mostrarMenu() {
		System.out.println("1. Crear cuenta");
		System.out.println("2. Ingresar dinero");
		System.out.println("3. Retirar dinero");
		System.out.println("4. Mostrar información cuenta");
		System.out.println("5. Salir");
	}
	

}
