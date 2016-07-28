package com.mbr.dao;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.jboss.tools.examples.model.Member;
import org.jboss.tools.examples.model.Rol;

import com.mbr.interfaces.dao.IMemberDao;
import com.mbr.interfaces.dao.IRolDao;

/**
 * 
 * @author mauriciobejaranorivera
 *
 */
@Stateless
public class MemberDao extends DataAccessObjectJpa<Member> implements IMemberDao{

	private @Inject IRolDao rolDao;
	
	public MemberDao(){
		super(Member.class);
	}

	@Override
	public Member registrar(Member member){
		try{
			member = create(member);
			Rol rol = new Rol();
			rol.setDescripcion("n/a");
			rol.setEstado("AC");
			rol.setFechaRegistro(new Date());
			rol.setNombre(new Date()+"");
			rol.setUsuarioRegistro("test");
			rol = rolDao.create(rol);
			//int x = Integer.parseInt("x");	
			return member;
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
	public Member modificar(Member rol){
		try{
			rol = update(rol);
			System.out.println("Modificación Correcta Rol "+rol.getId());
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
	public boolean eliminar(Member rol) {
		return false;
	}
	
    public Member findById(Long id) {
        return getEntityManager().find(Member.class, id);
    }

    public Member findByEmail(String email) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Member> criteria = cb.createQuery(Member.class);
        Root<Member> member = criteria.from(Member.class);
        // Swap criteria statements if you would like to try out type-safe criteria queries, a new
        // feature in JPA 2.0
        // criteria.select(member).where(cb.equal(member.get(Member_.name), email));
        criteria.select(member).where(cb.equal(member.get("email"), email));
        return getEntityManager().createQuery(criteria).getSingleResult();
    }

    public List<Member> findAllOrderedByName() {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Member> criteria = cb.createQuery(Member.class);
        Root<Member> member = criteria.from(Member.class);
        // Swap criteria statements if you would like to try out type-safe criteria queries, a new
        // feature in JPA 2.0
        // criteria.select(member).orderBy(cb.asc(member.get(Member_.name)));
        criteria.select(member).orderBy(cb.asc(member.get("name")));
        return getEntityManager().createQuery(criteria).getResultList();
    }


}
