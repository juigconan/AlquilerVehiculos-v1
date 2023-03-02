package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;

public class Alquileres {

	private List<Alquiler> coleccionAlquileres;

	public Alquileres() {
		coleccionAlquileres = new ArrayList<>();
	}

	public List<Alquiler> get() {
		return new ArrayList<>(coleccionAlquileres);
	}

	public List<Alquiler> get(Cliente cliente) {
		List<Alquiler> alquileresCliente = new ArrayList<>();
		for (Alquiler alquiler : coleccionAlquileres) {
			if (alquiler.getCliente().equals(cliente)) {
				alquileresCliente.add(alquiler);
			}
		}
		return alquileresCliente;
	}

	public List<Alquiler> get(Vehiculo turismo) {
		List<Alquiler> alquileresTurismo = new ArrayList<>();
		for (Alquiler alquiler : coleccionAlquileres) {
			if (turismo.equals(alquiler.getVehiculo())) {
				alquileresTurismo.add(alquiler);
			}
		}
		return alquileresTurismo;
	}

	public int getCantidad() {
		return coleccionAlquileres.size();
	}

	private void comprobarAlquiler(Cliente cliente, Vehiculo turismo, LocalDate fechaAlquiler)
			throws OperationNotSupportedException {

		for (Alquiler alquiler : coleccionAlquileres) {
			LocalDate fechaDevolucion = alquiler.getFechaDevolucion();
			Cliente esteCliente = alquiler.getCliente();
			Vehiculo esteVehiculo = alquiler.getVehiculo();
			if (fechaDevolucion == null) {
				if (esteCliente.equals(cliente)) {
					throw new OperationNotSupportedException("ERROR: El cliente tiene otro alquiler sin devolver.");
				}
				if (esteVehiculo.equals(turismo)) {
					throw new OperationNotSupportedException("ERROR: El vehículo está actualmente alquilado.");
				}
				// Si la fecha de devolución no es anterior, asi nos ahorramos una comprobación
			} else if (!fechaDevolucion.isBefore(fechaAlquiler)) {
				if (esteCliente.equals(cliente)) {
					throw new OperationNotSupportedException("ERROR: El cliente tiene un alquiler posterior.");
				}
				if (esteVehiculo.equals(turismo)) {
					throw new OperationNotSupportedException("ERROR: El vehículo tiene un alquiler posterior.");
				}
			}

		}

	}

	public void devolver(Cliente cliente, LocalDate fechaDevolucion) throws OperationNotSupportedException {
		if (cliente == null) {
			throw new NullPointerException("ERROR: No se puede devolver un alquiler nulo.");
		}
		Alquiler alquiler = getAlquilerAbierto(cliente);
		if (alquiler == null) {
			throw new OperationNotSupportedException("ERROR: No existe ningún alquiler igual.");
		}

		coleccionAlquileres.get(coleccionAlquileres.indexOf(alquiler)).devolver(fechaDevolucion);
	}

	private Alquiler getAlquilerAbierto(Cliente cliente) {
		Alquiler alquilerADevolver = null;
		Iterator<Alquiler> iterador = coleccionAlquileres.iterator();
		while (alquilerADevolver == null && iterador.hasNext()) {
			Alquiler alquiler = iterador.next();
			if (alquiler.getCliente().equals(cliente) && alquiler.getFechaDevolucion() == null) {
				alquilerADevolver = alquiler;
			}
		}
		return alquilerADevolver;
	}

	public void devolver(Vehiculo vehiculo, LocalDate fechaDevolucion) throws OperationNotSupportedException {
		if (vehiculo == null) {
			throw new NullPointerException("ERROR: No se puede devolver un alquiler nulo.");
		}
		if (buscar(cliente) == null) {
			throw new OperationNotSupportedException("ERROR: No existe ningún alquiler igual.");
		}
		coleccionAlquileres.get(coleccionAlquileres.indexOf(alquiler)).devolver(fechaDevolucion);
	}

	public void insertar(Alquiler alquiler) throws OperationNotSupportedException {
		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede insertar un alquiler nulo.");
		}
		comprobarAlquiler(alquiler.getCliente(), alquiler.getVehiculo(), alquiler.getFechaAlquiler());
		coleccionAlquileres.add(alquiler);
	}

	public Alquiler buscar(Alquiler alquiler) {
		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede buscar un alquiler nulo.");
		}
		int indice = coleccionAlquileres.indexOf(alquiler);
		return indice == -1 ? null : coleccionAlquileres.get(indice);
	}

	public void borrar(Alquiler alquiler) throws OperationNotSupportedException {
		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede borrar un alquiler nulo.");
		}
		if (!coleccionAlquileres.contains(alquiler)) {
			throw new OperationNotSupportedException("ERROR: No existe ningún alquiler igual.");
		}
		coleccionAlquileres.remove(alquiler);
	}

}
