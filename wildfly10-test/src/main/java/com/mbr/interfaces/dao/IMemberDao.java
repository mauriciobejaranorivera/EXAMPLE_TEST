package com.mbr.interfaces.dao;

import java.util.List;

import org.jboss.tools.examples.model.Member;


/**
 * Interface used to interact with the Member.
 * 
 * @author mauriciobejaranorivera
 *
 */
public interface IMemberDao {

	/**
	 * Use only intermediate transactions
	 * 
	 * @param rol
	 * @return
	 */
	Member create(Member rol);

	/**
	 * Use only intermediate transactions
	 * 
	 * @param rol
	 * @return
	 */
	Member update(Member rol);

	/**
	 * registrar object
	 * 
	 * @param Rol
	 * @return Rol
	 */
	Member registrar(Member rol);

	/**
	 * modificar object
	 * 
	 * @param Rol
	 * @return Rol
	 */
	Member modificar(Member rol);

	/**
	 * eliminar object
	 * 
	 * @param menuAccion
	 * @return
	 */
	boolean eliminar(Member rol);
	
	Member findById(Long id);
	
	Member findByEmail(String email);
	
	List<Member> findAllOrderedByName();

}
