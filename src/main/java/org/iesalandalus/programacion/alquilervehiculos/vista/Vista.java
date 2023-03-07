package org.iesalandalus.programacion.alquilervehiculos.vista;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.controlador.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.vista.texto.Accion;
import org.iesalandalus.programacion.alquilervehiculos.vista.texto.Consola;

public class Vista {

	Controlador controlador;

	public void setControlador(Controlador controlador) {
		if (controlador == null) {
			throw new NullPointerException("ERROR: El controlador no puede ser nulo.");
		}
		this.controlador = controlador;
	}

	public void comenzar() {
		Accion accion;
		do {
			Consola.mostrarCabecera("Menu principal");
			Consola.mostrarMenu();
			accion = Consola.elegirAccion();
			accion.ejecutar();
		} while (accion != Accion.SALIR);
	}

	public void terminar() {
		System.out.print("¡Gracias por utilizar nuestra aplicacion!");
	}

	public void insertarCliente() {
		Consola.mostrarCabecera("Insertar cliente");
		try {
			controlador.insertarCliente(Consola.leerCliente());
			System.out.println("Cliente insertado correctamente");
		} catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}

	public void insertarVehiculo() {
		Consola.mostrarCabecera("Insertar turismo");
		try {
			controlador.insertarTurismo(Consola.leerVehiculo());
			System.out.println("Turismo insertado correctamente.");
		} catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
			System.out.println(e.getMessage());
		}

	}

	public void insertarAlquiler() {
		Consola.mostrarCabecera("Insertar alquiler");
		try {
			controlador.insertarAlquiler(Consola.leerAlquiler());
			System.out.println("Alquiler insertado correctamente.");
		} catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
			System.out.println(e.getMessage());
		}

	}

	public void buscarCliente() {
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

	public void buscarVehiculo() {
		Consola.mostrarCabecera("Insertar turismo");
		Vehiculo turismoABuscar = Consola.leerVehiculoMatricula();
		Vehiculo turismo = null;
		try {
			turismo = controlador.buscar(turismoABuscar);
		} catch (IllegalArgumentException | NullPointerException e) {
			System.out.println(e.getMessage());
		}
		System.out.println(turismo != null ? turismo.toString() : "No existe el turismo");
	}

	public void buscarAlquiler() {
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

	public void modificarCliente() {
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

	public void devolverAlquilerCliente() {
		Consola.mostrarCabecera("Devolver alquiler");
		try {
			controlador.devolverAlquiler(Consola.leerClienteDni(), Consola.leerFechaDevolucion());
			System.out.println("Alquiler devuelto correctamente.");
		} catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void devolverAlquilerVehiculo() {
		Consola.mostrarCabecera("Devolver alquiler");
		try {
			controlador.devolverAlquiler(Consola.leerVehiculoMatricula(), Consola.leerFechaDevolucion());
			System.out.println("Alquiler devuelto correctamente.");
		} catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}

	public void borrarCliente() {
		Consola.mostrarCabecera("Borrar cliente");
		try {
			controlador.borrarCliente(Consola.leerCliente());
			System.out.println("Cliente borrado correctamente.");
		} catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}

	public void borrarVehiculo() {
		Consola.mostrarCabecera("Borrar turismo");
		try {
			controlador.borrarTurismo(Consola.leerVehiculo());
			System.out.println("Turismo borrado correctamente");
		} catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}

	public void borrarAlquiler() {
		Consola.mostrarCabecera("Borrar alquiler");
		try {
			controlador.borrarAlquiler(Consola.leerAlquiler());
			System.out.println("Alquiler borrado correctamente.");
		} catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}

	public void listarClientes() {
		Consola.mostrarCabecera("Listado de clientes");
		StringBuilder listado = new StringBuilder();
		for (Cliente cliente : controlador.getClientes()) {
			listado.append(cliente).append(System.getProperty("line.separator"));
		}
		System.out.println(listado.isEmpty() ? "No existe ningún cliente." : listado);
	}

	public void listarVehiculos() {
		Consola.mostrarCabecera("Listado de turismos");
		StringBuilder listado = new StringBuilder();
		for (Vehiculo turismo : controlador.getTurismos()) {
			listado.append(turismo).append(System.getProperty("line.separator"));
		}
		System.out.println(listado.isEmpty() ? "No existe ningún turismo." : listado);
	}

	public void listarAlquileres() {
		Consola.mostrarCabecera("Listado de alquileres");
		StringBuilder listado = new StringBuilder();
		for (Alquiler alquiler : controlador.getAlquileres()) {
			listado.append(alquiler).append(System.getProperty("line.separator"));
		}
		System.out.println(listado.isEmpty() ? "No existe ningún alquiler." : listado);
	}

	public void listarAlquileresCliente() {
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

	public void listarAlquileresVehiculo() {
		Consola.mostrarCabecera("Listado de alquileres del turismo");
		StringBuilder listado = new StringBuilder();
		Vehiculo turismoABuscar = null;
		try {
			turismoABuscar = Consola.leerVehiculoMatricula();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		for (Alquiler alquiler : controlador.getAlquileresTurismo(turismoABuscar)) {
			listado.append(alquiler).append(System.getProperty("line.separator"));
		}
		System.out.println(listado.isEmpty() ? "Este turismo no tiene ningún alquiler." : listado);
	}

}
