package org.iesalandalus.programacion.alquilervehiculos.vista;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.controlador.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;

public class Vista {

	Controlador controlador;

	public void setControlador(Controlador controlador) {
		if (controlador == null) {
			throw new NullPointerException("ERROR: El controlador no puede ser nulo.");
		}
		this.controlador = controlador;
	}

	public void comenzar() {
		Opcion opcion;
		do {
			Consola.mostrarCabecera("Menu principal");
			Consola.mostrarMenu();
			opcion = Consola.elegirOpcion();
			ejecutar(opcion);
		} while (opcion != Opcion.SALIR);
	}

	public void terminar() {
		System.out.print("¡Gracias por utilizar nuestra aplicacion!");
	}

	private void ejecutar(Opcion opcion) {

		switch (opcion) {
		case SALIR: {
			controlador.terminar();
			break;
		}
		case INSERTAR_CLIENTE: {
			insertarCliente();
			break;
		}
		case INSERTAR_TURISMO: {
			insertarTurismo();
			break;
		}
		case INSERTAR_ALQUILER: {
			insertarAlquiler();
			break;
		}
		case BUSCAR_CLIENTE: {
			buscarCliente();
			break;
		}
		case BUSCAR_TURISMO: {
			buscarTurismo();
			break;
		}
		case BUSCAR_ALQUILER: {
			buscarAlquiler();
			break;
		}
		case MODIFICAR_CLIENTE: {
			modificarCliente();
			break;
		}
		case DEVOLVER_ALQUILER: {
			devolverAlquiler();
			break;
		}
		case BORRAR_CLIENTE: {
			borrarCliente();
			break;
		}
		case BORRAR_TURISMO: {
			borrarTurismo();
			break;
		}
		case BORRAR_ALQUILER: {
			borrarAlquiler();
			break;
		}
		case LISTAR_CLIENTES: {
			listarClientes();
			break;
		}
		case LISTAR_TURISMOS: {
			listarTurismos();
			break;
		}
		case LISTAR_ALQUILERES: {
			listarAlquileres();
			break;
		}
		case LISTAR_ALQUILERES_CLIENTE: {
			listarAlquileresCliente();
			break;
		}
		case LISTAR_ALQUILERES_TURISMO: {
			listarAlquileresTurismo();
			break;
		}
		default: {
			// Nunca deberiamos llegar a esta excepción
			throw new IllegalArgumentException("ERROR: Opción no valida.");
		}

		}

	}

	private void insertarCliente() {
		Consola.mostrarCabecera("Insertar cliente");
		try {
			controlador.insertarCliente(Consola.leerCliente());
			System.out.println("Cliente insertado correctamente");
		} catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}

	private void insertarTurismo() {
		Consola.mostrarCabecera("Insertar turismo");
		try {
			controlador.insertarTurismo(Consola.leerTurismo());
			System.out.println("Turismo insertado correctamente.");
		} catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
			System.out.println(e.getMessage());
		}

	}

	private void insertarAlquiler() {
		Consola.mostrarCabecera("Insertar alquiler");
		try {
			controlador.insertarAlquiler(Consola.leerAlquiler());
			System.out.println("Alquiler insertado correctamente.");
		} catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
			System.out.println(e.getMessage());
		}

	}

	private void buscarCliente() {
		Consola.mostrarCabecera("Insertar cliente");
		Cliente clienteABuscar = Consola.leerClienteDni();
		Cliente cliente = null;
		try {
			cliente = controlador.buscar(clienteABuscar);
		} catch (IllegalArgumentException | NullPointerException e) {
			System.out.println(e.getMessage());
		}
		System.out.println(cliente != null ? cliente.toString() : "No existe el cliente");
	}

	private void buscarTurismo() {
		Consola.mostrarCabecera("Insertar turismo");
		Turismo turismoABuscar = Consola.leerTurismoMatricula();
		Turismo turismo = null;
		try {
			turismo = controlador.buscar(turismoABuscar);
		} catch (IllegalArgumentException | NullPointerException e) {
			System.out.println(e.getMessage());
		}
		System.out.println(turismo != null ? turismo.toString() : "No existe el turismo");
	}

	private void buscarAlquiler() {
		Consola.mostrarCabecera("Insertar alquiler");
		Alquiler alquilerABuscar = Consola.leerAlquiler();
		Alquiler alquiler = null;
		try {
			alquiler = controlador.buscar(alquilerABuscar);
		} catch (IllegalArgumentException | NullPointerException e) {
			System.out.println(e.getMessage());
		}
		System.out.println(alquiler != null ? alquiler.toString() : "No existe el alquiler");
	}

	private void modificarCliente() {
		Consola.mostrarCabecera("Modificar cliente");
		System.out.println("Inroduce los datos del cliente: ");
		try {
			Cliente cliente = Consola.leerCliente();
			String nombre = Consola.leerNombre();
			String telefono = Consola.leerTelefono();
			controlador.modificarCliente(cliente, nombre, telefono);
			System.out.println("Cliente modificado correctamente.");
		} catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}

	private void devolverAlquiler() {
		Consola.mostrarCabecera("Devolver alquiler");
		try {
			controlador.devolverAlquiler(Consola.leerAlquiler(), Consola.leerFechaDevolucion());
			System.out.println("Alquiler devuelto correctamente.");
		} catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}

	private void borrarCliente() {
		Consola.mostrarCabecera("Borrar cliente");
		try {
			controlador.borrarCliente(Consola.leerCliente());
			System.out.println("Cliente borrado correctamente.");
		} catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}

	private void borrarTurismo() {
		Consola.mostrarCabecera("Borrar turismo");
		try {
			controlador.borrarTurismo(Consola.leerTurismo());
			System.out.println("Turismo borrado correctamente");
		} catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}

	private void borrarAlquiler() {
		Consola.mostrarCabecera("Borrar alquiler");
		try {
			controlador.borrarAlquiler(Consola.leerAlquiler());
			System.out.println("Alquiler borrado correctamente.");
		} catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}

	private void listarClientes() {
		Consola.mostrarCabecera("Listado de clientes");
		StringBuilder listado = new StringBuilder();
		for (Cliente cliente : controlador.getClientes()) {
			// El system.getProperty esta mirado de Internet, pero lo que hace es coger el
			// separador de linea del sistema.
			listado.append(cliente).append(System.getProperty("line.separator"));
		}
		System.out.println(listado.isEmpty() ? "No existe ningún cliente." : listado);
	}

	private void listarTurismos() {
		Consola.mostrarCabecera("Listado de turismos");
		StringBuilder listado = new StringBuilder();
		for (Turismo turismo : controlador.getTurismos()) {
			listado.append(turismo).append(System.getProperty("line.separator"));
		}
		System.out.println(listado.isEmpty() ? "No existe ningún turismo." : listado);
	}

	private void listarAlquileres() {
		Consola.mostrarCabecera("Listado de alquileres");
		StringBuilder listado = new StringBuilder();
		for (Alquiler alquiler : controlador.getAlquileres()) {
			listado.append(alquiler).append(System.getProperty("line.separator"));
		}
		System.out.println(listado.isEmpty() ? "No existe ningún alquiler." : listado);
	}

	private void listarAlquileresCliente() {
		Consola.mostrarCabecera("Listado de alquileres del cliente");
		StringBuilder listado = new StringBuilder();
		Cliente clienteABuscar = null;
		try {
			clienteABuscar = Consola.leerClienteDni();
		} catch (IllegalArgumentException | NullPointerException e) {
			System.out.println(e.getMessage());
		}
		for (Alquiler alquiler : controlador.getAlquileresCliente(clienteABuscar)) {

			listado.append(alquiler).append(System.getProperty("line.separator"));
		}
		System.out.println(listado.isEmpty() ? "Este cliente no tiene ningún alquiler." : listado);

	}

	private void listarAlquileresTurismo() {
		Consola.mostrarCabecera("Listado de alquileres del turismo");
		StringBuilder listado = new StringBuilder();
		Turismo turismoABuscar = null;
		try {
			turismoABuscar = Consola.leerTurismoMatricula();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		for (Alquiler alquiler : controlador.getAlquileresTurismo(turismoABuscar)) {
			listado.append(alquiler).append(System.getProperty("line.separator"));
		}
		System.out.println(listado.isEmpty() ? "Este turismo no tiene ningún alquiler." : listado);
	}

}
