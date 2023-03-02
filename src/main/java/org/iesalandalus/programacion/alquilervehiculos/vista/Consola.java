package org.iesalandalus.programacion.alquilervehiculos.vista;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.utilidades.Entrada;

public class Consola {

	private static final String PATRON_FECHA = "dd/MM/yyyy";
	private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	private Consola() {

	}

	public static void mostrarCabecera(String mensaje) {
		System.out.printf("%n%s%n", mensaje);
		StringBuilder subrayado = new StringBuilder();
		for (int i = 0; i < mensaje.length(); i++) {
			subrayado.append("-");
		}
		System.out.printf("%s%n%n", subrayado);
	}

	public static void mostrarMenu() {
		for (Opcion opcion : Opcion.values()) {
			if (opcion.ordinal() != 0) {
				System.out.printf("%s%n", opcion);
			}
		}
		System.out.println(Opcion.SALIR);
	}

	private static String leerCadena(String mensaje) {
		System.out.printf("Introduce %s", mensaje);
		return Entrada.cadena();
	}

	private static int leerEntero(String mensaje) {
		System.out.printf("Introduce %s", mensaje);
		return Entrada.entero();
	}

	private static LocalDate leerFecha(String mensaje) {
		System.out.printf("Introduce %s (%s)", mensaje, PATRON_FECHA);
		LocalDate fecha = null;
		try {
			fecha = LocalDate.parse(Entrada.cadena(), FORMATO_FECHA);
		} catch (Exception e) {
			throw new IllegalArgumentException("ERROR: La fecha introducida no es válida.");
		}

		return fecha;
	}

	public static Opcion elegirOpcion() {
		Opcion opcion = null;
		do {
			try {
				opcion = Opcion.get(leerEntero("la opcion deseada:"));
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		} while (opcion == null);

		return opcion;
	}

	public static Cliente leerCliente() {
		Cliente cliente = null;
		cliente = new Cliente(leerNombre(), leerCadena("el dni del cliente:"), leerTelefono());
		return cliente;
	}

	public static Cliente leerClienteDni() {
		Cliente cliente = null;
		cliente = Cliente.getClienteConDni(leerCadena("el dni del cliente:"));
		return cliente;
	}

	public static String leerNombre() {
		return leerCadena("el nombre del cliente:");
	}

	public static String leerTelefono() {
		return leerCadena("el teléfono del cliente:");
	}

	public static Turismo leerTurismo() {
		Turismo turismo = null;
		turismo = new Turismo(leerCadena("la marca del turismo:"), leerCadena("el modelo del turismo:"),
				leerEntero("la cilindrada del turismo:"), leerCadena("la matricula del turismo:"));
		return turismo;
	}

	public static Turismo leerTurismoMatricula() {
		Turismo turismo = null;
		turismo = Turismo.getTurismoConMatricula(leerCadena("la matricula del turismo:"));
		return turismo;

	}

	public static Alquiler leerAlquiler() {
		Alquiler alquiler = null;
		alquiler = new Alquiler(leerClienteDni(), leerTurismoMatricula(), leerFecha("la fecha del alquiler:"));
		return alquiler;
	}

	public static LocalDate leerFechaDevolucion() {
		return leerFecha("la fecha de devolución:");
	}

}
