package com.mbr.interfaces.dao;

import org.jboss.tools.examples.model.Rol;


/**
 * Interface used to interact with the Rol.
 * 
 * @author mauriciobejaranorivera
 *
 */
public interface IRolDao {

	/**
	 * Use only intermediate transactions
	 * 
	 * @param rol
	 * @return
	 */
	Rol create(Rol rol);

	/**
	 * Use only intermediate transactions
	 * 
	 * @param rol
	 * @return
	 */
	Rol update(Rol rol);

	/**
	 * registrar object
	 * 
	 * @param Rol
	 * @return Rol
	 */
	Rol registrar(Rol rol);

	/**
	 * modificar object
	 * 
	 * @param Rol
	 * @return Rol
	 */
	Rol modificar(Rol rol);

	/**
	 * eliminar object
	 * 
	 * @param menuAccion
	 * @return
	 */
	boolean eliminar(Rol rol);

}
