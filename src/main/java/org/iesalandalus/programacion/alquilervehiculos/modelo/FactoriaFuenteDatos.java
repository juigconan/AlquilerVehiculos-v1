package org.iesalandalus.programacion.alquilervehiculos.modelo;

import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IFuenteDatos;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.memoria.FuenteDatosMemoria;

public enum FactoriaFuenteDatos {
	MEMORIA {
		@Override
		IFuenteDatos crear() {
			return new FuenteDatosMemoria();
		}
	};
	
	abstract IFuenteDatos crear();

}
