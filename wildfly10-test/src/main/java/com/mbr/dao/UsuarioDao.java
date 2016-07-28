package com.mbr.dao;

import java.util.Date;

import javax.ejb.Stateless;

import org.jboss.tools.examples.model.Usuario;
import org.jboss.tools.examples.util.FacesUtil;

import com.mbr.interfaces.dao.IUsuarioDao;

/**
 * 
 * @author mauriciobejaranorivera
 *
 */
@Stateless
public class UsuarioDao extends DataAccessObjectJpa<Usuario> implements IUsuarioDao {


	public UsuarioDao() {
		super(Usuario.class);
	}

	@Override
	public Usuario create(Usuario usuario) {
		return super.create(usuario);
	}

	@Override
	public Usuario update(Usuario usuario) {
		return super.update(usuario);
	}

	@Override
	public Usuario registrar(Usuario usuario) {
		try {
			usuario = create(usuario);
			FacesUtil.infoMessage("Registro Correcto",
					"Usuario " + usuario.getNombre());
			return usuario;
		} catch (Exception e) {
			String cause = e.getMessage();
			if (cause
					.contains("org.hibernate.exception.ConstraintViolationException: could not execute statement")) {
				FacesUtil.errorMessage("Ya existe un registro igual.");
			} else {
				FacesUtil.errorMessage("Error al modificar");
			}
			rollbackTransaction();
			return null;
		}
	}

	@Override
	public Usuario modificar(Usuario usuario) {
		try {
			usuario = update(usuario);
			FacesUtil.infoMessage("Modificación Correcta",
					"Usuario " + usuario.toString());
			return usuario;
		} catch (Exception e) {
			String cause = e.getMessage();
			if (cause
					.contains("org.hibernate.exception.ConstraintViolationException: could not execute statement")) {
				FacesUtil.errorMessage("Ya existe un registro igual.");
			} else {
				FacesUtil.errorMessage("Error al modificar");
			}
			rollbackTransaction();
			return null;
		}
	}

	@Override
	public boolean eliminar(Usuario usuario) {
		try {
			usuario.setState("RM");
			usuario.setLogin(new Date() + "|" + usuario.getLogin());
			Usuario bar = update(usuario);
			FacesUtil.infoMessage("Eliminación Correcta",
					"Usuario " + usuario.toString());
			return bar != null ? true : false;
		} catch (Exception e) {
			FacesUtil.errorMessage("Error al eliminar");
			rollbackTransaction();
			return false;
		}
	}

	@Override
	public Usuario obtenerPorLoginYPassword(String login, String password) {
		return findByParameterObjectTwo("login", "password", login, password);

	}
}
