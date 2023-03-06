package org.iesalandalus.programacion.alquilervehiculos.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Autobus;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Furgoneta;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IFuenteDatos;

public class ModeloCascada extends Modelo {

	public ModeloCascada(IFuenteDatos fuenteDatos) {
		setFuenteDatos(fuenteDatos);
		comenzar();
	}

	@Override
	void insertar(Cliente cliente) throws OperationNotSupportedException {
		getClientes().insertar(new Cliente(cliente));
	}

	@Override
	void insertar(Vehiculo vehiculo) throws OperationNotSupportedException {
		getVehiculos().insertar(vehiculo);

	}

	@Override
	void insertar(Alquiler alquiler) throws OperationNotSupportedException {
		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede realizar un alquiler nulo.");
		}
		Cliente cliente = getClientes().buscar(alquiler.getCliente());
		Vehiculo vehiculo = getVehiculos().buscar(alquiler.getVehiculo());
		if (cliente == null) {
			throw new OperationNotSupportedException("ERROR: No existe el cliente del alquiler.");
		}
		if (vehiculo == null) {
			throw new OperationNotSupportedException("ERROR: No existe el turismo del alquiler.");
		}

		getAlquileres().insertar(new Alquiler(cliente, vehiculo, alquiler.getFechaAlquiler()));

	}

	@Override
	Cliente buscar(Cliente cliente) {
		return new Cliente(getClientes().buscar(cliente));
	}

	@Override
	Alquiler buscar(Alquiler alquiler) {
		return new Alquiler(getAlquileres().buscar(alquiler));
	}

	@Override
	Vehiculo buscar(Vehiculo vehiculo) {
		Vehiculo vehiculoADevolver = null;
		if(vehiculo instanceof Turismo turismo) {
			vehiculoADevolver = getVehiculos().buscar(turismo);
		}
		if(vehiculo instanceof Autobus autobus) {
			vehiculoADevolver = getVehiculos().buscar(autobus);
		}
		if(vehiculo instanceof Furgoneta furgoneta) {
			vehiculoADevolver = getVehiculos().buscar(furgoneta);
		}
		//vehiculoADevolver = getVehiculos().buscar(vehiculo);
		return vehiculoADevolver;
	}

	@Override
	void modificar(Cliente cliente, String nombre, String telefono) throws OperationNotSupportedException {
		getClientes().modificar(cliente, nombre, telefono);

	}

	@Override
	void devolver(Cliente cliente, LocalDate fechaDevolucion) throws OperationNotSupportedException {
//		if (getAlquileres(). == null) {
//			throw new NullPointerException("ERROR: No se puede devolver un alquiler nulo.");
//		}
//		Alquiler alquilerADevolver = getAlquileres().buscar(alquiler);
//		if (alquilerADevolver == null) {
//			throw new OperationNotSupportedException("ERROR: No existe el alquiler a devolver.");
//		}
//		alquilerADevolver.devolver(fechaDevolucion);
		getAlquileres().devolver(cliente, fechaDevolucion);
	}
	
	@Override
	void devolver(Vehiculo vehiculo, LocalDate fechaDevolucion) throws OperationNotSupportedException {
//		if (alquiler == null) {
//			throw new NullPointerException("ERROR: No se puede devolver un alquiler nulo.");
//		}
//		Alquiler alquilerADevolver = getAlquileres().buscar(alquiler);
//		if (alquilerADevolver == null) {
//			throw new OperationNotSupportedException("ERROR: No existe el alquiler a devolver.");
//		}
//		alquilerADevolver.devolver(fechaDevolucion);
		getAlquileres().devolver(vehiculo, fechaDevolucion);
	}

	@Override
	void borrar(Cliente cliente) throws OperationNotSupportedException {
		for (Alquiler alquiler : getAlquileres().get(cliente)) {
			getAlquileres().borrar(alquiler);
		}
		getClientes().borrar(cliente);
	}

	@Override
	void borrar(Vehiculo vehiculo) throws OperationNotSupportedException {
		for (Alquiler alquiler : getAlquileres().get(vehiculo)) {
			getAlquileres().borrar(alquiler);
		}
		getVehiculos().borrar(vehiculo);
	}

	@Override
	void borrar(Alquiler alquiler) throws OperationNotSupportedException {
		getAlquileres().borrar(alquiler);
	}

	@Override
	List<Cliente> getListaClientes() {
		List<Cliente> listaADevolver = new ArrayList<>();
		for (Cliente cliente : getClientes().get()) {
			listaADevolver.add(new Cliente(cliente));
		}
		return listaADevolver;
	}

	@Override
	List<Vehiculo> getListaVehiculos() {

		List<Vehiculo> listaADevolver = new ArrayList<>();
		for (Vehiculo vehiculo : getVehiculos().get()) {
			if(vehiculo instanceof Turismo turismo) {
				listaADevolver.add(new Turismo(turismo));
			}
			if(vehiculo instanceof Autobus autobus) {
				listaADevolver.add(new Autobus(autobus));
			}
			if(vehiculo instanceof Furgoneta furgoneta) {
				listaADevolver.add(new Furgoneta(furgoneta));
			}
		}
		return listaADevolver;
	}

	@Override
	List<Alquiler> getListaAlquileres() {
		List<Alquiler> listaADevolver = new ArrayList<>();
		for (Alquiler alquiler : getAlquileres().get()) {
			listaADevolver.add(new Alquiler(alquiler));
		}
		return listaADevolver;
	}

	@Override
	List<Alquiler> getListaAlquileres(Cliente cliente) {

		List<Alquiler> listaADevolver = new ArrayList<>();
		for (Alquiler alquiler : getAlquileres().get(cliente)) {
			listaADevolver.add(new Alquiler(alquiler));
		}
		return listaADevolver;
	}
//TODO: Esta mal deberia ser lista y no solo get
	@Override
	List<Alquiler> getListaAlquileres(Vehiculo turismo) {
		List<Alquiler> listaADevolver = new ArrayList<>();
		for (Alquiler alquiler : getAlquileres().get(turismo)) {
			listaADevolver.add(new Alquiler(alquiler));
		}
		return listaADevolver;

	}

}
