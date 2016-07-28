package com.mbr.dao;

import javax.ejb.Stateless;

import org.jboss.tools.examples.model.Rol;

import com.mbr.interfaces.dao.IRolDao;

/**
 * 
 * @author mauriciobejaranorivera
 *
 */
@Stateless
public class RolDao extends DataAccessObjectJpa<Rol> implements IRolDao{

	public RolDao(){
		super(Rol.class);
	}

	@Override
	public Rol registrar(Rol rol){
		try{
			rol = create(rol);
			//int test = Integer.parseInt("z");
			return rol;
		}catch(Exception e){
			String cause=e.getMessage();
			if (cause.contains("org.hibernate.exception.ConstraintViolationException: could not execute statement")) {
				System.out.println("Ya existe un registro igual.");
			}else{
				System.out.println("Error al registrar");
			}
			rollbackTransaction();
			return null;
			
		}
	}

	@Override
	public Rol modificar(Rol rol){
		try{
			rol = update(rol);
			System.out.println("Modificación Correcta Rol "+rol.getNombre());
			return rol;
		}catch(Exception e){
			String cause=e.getMessage();
			if (cause.contains("org.hibernate.exception.ConstraintViolationException: could not execute statement")) {
				System.out.println("Ya existe un registro igual.");
			}else{
				System.out.println("Error al modificar");
			}
			rollbackTransaction();
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see com.mbr.interfaces.dao.IRolDao#eliminar(org.jboss.tools.examples.model.Rol)
	 */
	@Override
	public boolean eliminar(Rol rol) {
		return false;
	}

	
}
