package edu.java.cuentaCorriente;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Modelo de las cuentas corrientes a gestionar
 * @author garfe *
 */
public class CuentaCorriente {
	
	/*
	 * Si no definimos ningún constructor por campos,
	 * se puede utilizar el constructor vacío por defecto.
	 * En el momento que se defina un constructor por campos
	 * específico, si se quiere usar el constructor por defecto
	 * será necesario definirlo en la clase de forma explícita.
	 */
	
	public CuentaCorriente(String dni, String nombreTitular, double saldo) {
		super();
		this.dni = dni;
		this.nombreTitular = nombreTitular;
		this.saldo = saldo;
	}

	public CuentaCorriente() {
		super();
	}

	//Atributos
	/*
	 * Modificador private: solo la misma clase podrá acceder a los
	 * atributos de forma directa.
	 */
	
	private String dni;
	private String nombreTitular;
	private double saldo;
	
	//Getters y Setters
	/*
	 * Se utilizar para que software externo a la clase pueda 
	 * tener acceso a los atributos.
	 */
	
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getNombreTitular() {
		return nombreTitular;
	}
	public void setNombreTitular(String nombreTitular) {
		this.nombreTitular = nombreTitular;
	}
	public double getSaldo() {
		return saldo;
	}
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	
	//Sobre escritura toString
	@Override
	public String toString() {
		return "CuentaCorriente [dni=" + dni + ", nombreTitular=" + nombreTitular + ", saldo=" + saldo + "]";
	}
	
	//Métodos
	public CuentaCorriente crearCuenta (List<CuentaCorriente> bd) {
		
		System.out.println("CREAR CUENTA: ");
		Scanner entradaDni = new Scanner(System.in);	
		Scanner entradaNombreTitular = new Scanner(System.in);	
		String opcionEntradaDni;
		String opcionEntradaNombreTitular;
		System.out.println("Introduza el DNI del titular: ");
		opcionEntradaDni = entradaDni.next();
		System.out.println("Introduza el nombre completo del titular: ");
		opcionEntradaNombreTitular = entradaNombreTitular.next();
		
		//Opción 1, constructor vacío
		CuentaCorriente cccVacio = new CuentaCorriente();
		cccVacio.setDni(opcionEntradaDni);
		cccVacio.setNombreTitular(opcionEntradaNombreTitular);
		cccVacio.setSaldo(0);
		return cccVacio;

		//Opción 2, constructor con todos los campos
		/*CuentaCorriente cccInicializado = new CuentaCorriente(opcionEntradaDni, opcionEntradaNombreTitular, 0);
		return cccInicializado;*/
	}
	
	public List<CuentaCorriente> ingresoCuenta(List<CuentaCorriente> bd) {
		System.out.println("INGRESO CUENTA: ");
		//pedir dni
		Scanner entradaDni = new Scanner(System.in);
		System.out.println("Indique dni de cuenta: ");
		String opcionEntradaDni = entradaDni.next();
		//buscar la cuenta
		int contador = 0;
		boolean esEncontrado = false;
		for(CuentaCorriente cuenta: bd) {			
			String dniBd = cuenta.getDni();
			if(dniBd.equals(opcionEntradaDni)) {
				esEncontrado = true;
				break;
			}
			contador++;			
		}		

		if(esEncontrado) {
			Scanner entradaIngreso = new Scanner(System.in);			
			System.out.println("Indique saldo a ingresar: ");
			double ingreso = entradaIngreso.nextDouble();
			double saldoActual = bd.get(contador).getSaldo();
			bd.get(contador).setSaldo(saldoActual+ingreso);
			double saldoNuevo = saldoActual+ingreso;
			
			System.out.println("Saldo anterior: "+saldoActual+" Saldo nuevo: "+saldoNuevo);
			
		}else {
			System.out.println("No existe cuenta para el dni indicado: "+opcionEntradaDni);
			return bd;
		}
		return bd;
	}
	
	public void mostrarCuentasUsuario(List<CuentaCorriente> bd){
		Scanner entradaDni = new Scanner(System.in);
		System.out.println("Indique dni de cuenta: ");
		String dniPosible = entradaDni.next();
		
		int contador=0;
		boolean existe=false;
		//Comprobación de la existencia de la cuenta con el dni
		for(CuentaCorriente infoCuenta: bd) {
			String dniOriginal = infoCuenta.getDni();
			if(dniOriginal.equals(dniPosible)) {
				existe=true;
				break;
			} 
			contador++;
		}
		//Presentación de los datos en caso de que la cuenta esté ya creada
		if(existe) {
			System.out.println("INFORMACIÓN DE LA CUENTA CORRIENTE: ");
			System.out.println("DNI: "+bd.get(contador).getDni()); 
			System.out.println("Nombre de titular: "+bd.get(contador).getNombreTitular());
			System.out.println("Saldo: "+ bd.get(contador).getSaldo());
		} else {
			System.out.println("La cuenta con dni "+ dniPosible +" no se encuentra");
		}
					
	}
	public List<CuentaCorriente> sacarDinero(List<CuentaCorriente> bd) {
		System.out.println("RETIRO DE SALDO: ");
		//Comprobación
		Scanner entradaDni = new Scanner(System.in);
		System.out.println("Indique dni de cuenta: ");
		String opcionEntradaDni = entradaDni.next();
		//buscar la cuenta
		int contador = 0;
		boolean esEncontrado = false;
		for(CuentaCorriente cuenta: bd) {			
			String dniBd = cuenta.getDni();
			if(dniBd.equals(opcionEntradaDni)) {
				esEncontrado = true;
				break;
			}
			contador++;			
		}
		if(esEncontrado) {
			System.out.println("Saldo en cuenta: "+bd.get(contador).getSaldo());
			Scanner entradaRetiro = new Scanner(System.in);			
			System.out.println("Indique saldo a retirar: ");
			double retiro = entradaRetiro.nextDouble();
			double saldoActual = bd.get(contador).getSaldo();
			if(saldoActual<retiro)
				System.err.println("** Saldo en cuenta insuficiente **");
			else {
				bd.get(contador).setSaldo(saldoActual-retiro);	
				double saldoNuevo = saldoActual-retiro;
				System.out.println("Saldo anterior: "+saldoActual+" Saldo nuevo: "+saldoNuevo);
			}						
		}else {
			System.out.println("No existe cuenta para el dni indicado: "+opcionEntradaDni);
			return bd;
		}
		return bd;

	}
	
	
	
}
