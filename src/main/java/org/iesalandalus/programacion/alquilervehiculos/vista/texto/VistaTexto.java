package org.iesalandalus.programacion.alquilervehiculos.vista.texto;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Autobus;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Furgoneta;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.vista.Vista;

public class VistaTexto extends Vista {

	public VistaTexto() {
		Accion.setVista(this);
	}

	@Override
	public void comenzar() {
		Accion accion;
		do {
			Consola.mostrarCabecera("Menu principal");
			Consola.mostrarMenu();
			accion = Consola.elegirAccion();
			accion.ejecutar();
		} while (accion != Accion.SALIR);
	}

	@Override
	public void terminar() {
		System.out.print("¡Gracias por utilizar nuestra aplicacion!");

	}

	public void insertarCliente() {
		Consola.mostrarCabecera("Insertar cliente");
		try {
			getControlador().insertarCliente(Consola.leerCliente());
			System.out.println("Cliente insertado correctamente");
		} catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}

	public void insertarVehiculo() {
		try {
			getControlador().insertarVehiculo(Consola.leerVehiculo());
			System.out.println("Vehículo insertado correctamente.");
		} catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
			System.out.println(e.getMessage());
		}

	}

	public void insertarAlquiler() {
		Consola.mostrarCabecera("Insertar alquiler");
		try {
			getControlador().insertarAlquiler(Consola.leerAlquiler());
			System.out.println("Alquiler insertado correctamente.");
		} catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
			System.out.println(e.getMessage());
		}

	}

	public void buscarCliente() {
		Consola.mostrarCabecera("Buscar cliente");
		Cliente clienteABuscar = Consola.leerClienteDni();
		Cliente cliente = null;
		try {
			cliente = getControlador().buscar(clienteABuscar);
		} catch (IllegalArgumentException | NullPointerException e) {
			System.out.println(e.getMessage());
		}
		System.out.println(cliente != null ? cliente.toString() : "No existe el cliente");
	}

	public void buscarVehiculo() {
		Consola.mostrarCabecera("Buscar vehículo");
		Vehiculo vehiculoABuscar = Consola.leerVehiculoMatricula();
		Vehiculo vehiculo = null;
		try {
			vehiculo = getControlador().buscar(vehiculoABuscar);
		} catch (IllegalArgumentException | NullPointerException e) {
			System.out.println(e.getMessage());
		}
		System.out.println(vehiculo != null ? vehiculo.toString() : "No existe el vehículo");
	}

	public void buscarAlquiler() {
		Consola.mostrarCabecera("Buscar alquiler");
		Alquiler alquilerABuscar = Consola.leerAlquiler();
		Alquiler alquiler = null;
		try {
			alquiler = getControlador().buscar(alquilerABuscar);
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
			getControlador().modificarCliente(cliente, nombre, telefono);
			System.out.println("Cliente modificado correctamente.");
		} catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}

	public void devolverAlquilerCliente() {
		Consola.mostrarCabecera("Devolver alquiler por cliente");
		try {
			getControlador().devolverAlquiler(Consola.leerClienteDni(), Consola.leerFechaDevolucion());
			System.out.println("Alquiler devuelto correctamente.");
		} catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}

	public void devolverAlquilerVehiculo() {
		Consola.mostrarCabecera("Devolver alquiler por vehículo");
		try {
			getControlador().devolverAlquiler(Consola.leerVehiculoMatricula(), Consola.leerFechaDevolucion());
			System.out.println("Alquiler devuelto correctamente.");
		} catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}

	public void borrarCliente() {
		Consola.mostrarCabecera("Borrar cliente");
		try {
			getControlador().borrarCliente(Consola.leerCliente());
			System.out.println("Cliente borrado correctamente.");
		} catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}

	public void borrarVehiculo() {
		Consola.mostrarCabecera("Borrar vehículo");
		try {
			getControlador().borrarVehiculo(Consola.leerVehiculo());
			System.out.println("Turismo borrado correctamente");
		} catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}

	public void borrarAlquiler() {
		Consola.mostrarCabecera("Borrar alquiler");
		try {
			getControlador().borrarAlquiler(Consola.leerAlquiler());
			System.out.println("Alquiler borrado correctamente.");
		} catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}

	public void listarClientes() {
		Consola.mostrarCabecera("Listado de clientes");
		List<Cliente> listaClientes = getControlador().getClientes();
		listaClientes.sort(Comparator.comparing(Cliente::getNombre).thenComparing(Cliente::getDni));
		StringBuilder listado = new StringBuilder();
		for (Cliente cliente : listaClientes) {
			listado.append(cliente).append(System.getProperty("line.separator"));
		}
		System.out.println(listado.isEmpty() ? "No existe ningún cliente." : listado);
	}

	public void listarVehiculos() {
		Consola.mostrarCabecera("Listado de vehíulos");
		List<Vehiculo> listaClientes = getControlador().getVehiculos();
		listaClientes.sort(Comparator.comparing(Vehiculo::getMarca).thenComparing(Vehiculo::getModelo)
				.thenComparing(Vehiculo::getMatricula));
		StringBuilder listado = new StringBuilder();
		for (Vehiculo vehículo : getControlador().getVehiculos()) {
			listado.append(vehículo).append(System.getProperty("line.separator"));
		}
		System.out.println(listado.isEmpty() ? "No existe ningún vehíulo." : listado);
	}

	public void listarAlquileres() {
		Consola.mostrarCabecera("Listado de alquileres");
		Comparator<Cliente> comparadorCliente = Comparator.comparing(Cliente::getNombre).thenComparing(Cliente::getDni);
		List<Alquiler> listaAlquileres = getControlador().getAlquileres();
		listaAlquileres.sort(Comparator.comparing(Alquiler::getFechaAlquiler).thenComparing(Alquiler::getCliente,
				comparadorCliente));
		StringBuilder listado = new StringBuilder();
		for (Alquiler alquiler : listaAlquileres) {
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
		Comparator<Cliente> comparadorCliente = Comparator.comparing(Cliente::getNombre).thenComparing(Cliente::getDni);
		List<Alquiler> listaAlquileres = getControlador().getAlquileresCliente(clienteABuscar);
		listaAlquileres.sort(Comparator.comparing(Alquiler::getFechaAlquiler).thenComparing(Alquiler::getCliente,
				comparadorCliente));
		for (Alquiler alquiler : listaAlquileres) {
			listado.append(alquiler).append(System.getProperty("line.separator"));
		}

		if (clienteABuscar != null) {
			System.out.println(listado.isEmpty() ? "Este cliente no tiene ningún alquiler." : listado);
		}

	}

	public void listarAlquileresVehiculo() {
		Consola.mostrarCabecera("Listado de alquileres del vehículo");
		StringBuilder listado = new StringBuilder();
		Vehiculo vehiculoABuscar = null;
		try {
			vehiculoABuscar = Consola.leerVehiculoMatricula();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		Comparator<Vehiculo> comparadorVehiculo = Comparator.comparing(Vehiculo::getMarca)
				.thenComparing(Vehiculo::getModelo).thenComparing(Vehiculo::getMatricula);
		List<Alquiler> listaAlquileres = getControlador().getAlquileresVehiculo(vehiculoABuscar);
		listaAlquileres.sort(Comparator.comparing(Alquiler::getFechaAlquiler).thenComparing(Alquiler::getVehiculo,
				comparadorVehiculo));
		for (Alquiler alquiler : getControlador().getAlquileresVehiculo(vehiculoABuscar)) {
			listado.append(alquiler).append(System.getProperty("line.separator"));
		}
		if (vehiculoABuscar != null) {
			System.out.println(listado.isEmpty() ? "Este vehículo no tiene ningún alquiler." : listado);
		}
	}

	public void mostrarEstadisticaMensualesTipoVehiculo() {
		Map<TipoVehiculo, Integer> mapa = inicializaEstadisticas();
		for (Map.Entry<TipoVehiculo, Integer> entrada : mapa.entrySet()) {
			TipoVehiculo llave = entrada.getKey();
			Integer numero = entrada.getValue();
			System.out.printf("%s --> %d%n", llave, numero);
		}
	}

	private Map<TipoVehiculo, Integer> inicializaEstadisticas() {
		Map<TipoVehiculo, Integer> mapaEstadisticas = new TreeMap<>();
		mapaEstadisticas.put(TipoVehiculo.TURISMO, 0);
		mapaEstadisticas.put(TipoVehiculo.FURGONETA, 0);
		mapaEstadisticas.put(TipoVehiculo.AUTOBUS, 0);
		for (Alquiler alquiler : getControlador().getAlquileres()) {
			if (alquiler.getVehiculo() instanceof Turismo) {
				mapaEstadisticas.put(TipoVehiculo.TURISMO, mapaEstadisticas.get(TipoVehiculo.TURISMO) + 1);
			}
			if (alquiler.getVehiculo() instanceof Autobus) {
				mapaEstadisticas.put(TipoVehiculo.AUTOBUS, mapaEstadisticas.get(TipoVehiculo.AUTOBUS) + 1);
			}
			if (alquiler.getVehiculo() instanceof Furgoneta) {
				mapaEstadisticas.put(TipoVehiculo.FURGONETA, mapaEstadisticas.get(TipoVehiculo.FURGONETA) + 1);
			}
		}
		return mapaEstadisticas;
	}

}