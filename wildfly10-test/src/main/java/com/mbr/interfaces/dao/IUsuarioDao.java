package com.mbr.interfaces.dao;

import org.jboss.tools.examples.model.Usuario;

/**
 * Interface used to interact with the Usuario.
 * 
 * @author mauriciobejaranorivera
 *
 */
public interface IUsuarioDao {

	/**
	 * Use only intermediate transactions
	 * 
	 * @param usuario
	 * @return
	 */
	Usuario create(Usuario usuario);

	/**
	 * Use only intermediate transactions
	 * 
	 * @param update
	 * @return
	 */
	Usuario update(Usuario update);

	/**
	 * registrar object
	 * 
	 * @param Usuario
	 * @return Usuario
	 */
	Usuario registrar(Usuario usuario);

	/**
	 * modificar object
	 * 
	 * @param Usuario
	 * @return Usuario
	 */
	Usuario modificar(Usuario usuario);

	/**
	 * eliminar object
	 * 
	 * @param usuario
	 * @return
	 */
	boolean eliminar(Usuario usuario);

	/**
	 * 
	 * @param login
	 * @param password
	 * @return Usuario
	 */
	Usuario obtenerPorLoginYPassword(String login, String password);

}
