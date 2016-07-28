package com.mbr.dao;

import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * 
 * @author mauriciobejaranorivera
 * 
 */
@Stateless
public class DataAccessObjectJpa<T> {

	@PersistenceContext(name="primary")
	private EntityManager em;

	private @Inject Event<Object> objectEventSrc;
	
    private @Resource EJBContext context;

	private Class<T> typeT;

	/**
	 * Default constructor
	 * 
	 */
	public DataAccessObjectJpa() {
		super();
	}

	/**
	 * Constructor
	 * 
	 * @param type
	 *            entity class
	 */
	public DataAccessObjectJpa(Class<T> typeT) {
		this.setTypeT(typeT);
	}

	//@Override
	public T create(T entity) {
		this.em.persist(entity);
		this.em.flush();
		this.em.refresh(entity);
		objectEventSrc.fire(entity);
		return entity;
	}

	//@Override
	public T update(T entity) {
		T t = (T) this.em.merge(entity);
		return t;
	}

	//@Override
	public boolean delete(Object id) {
		return true;
	}

	//@Override
	public void rollbackTransaction() {
		context.setRollbackOnly();
	}
	
	@Produces
	public EntityManager getEntityManager(){
		return this.em;
	}

	public Class<T> getTypeT() {
		return typeT;
	}

	public void setTypeT(Class<T> typeT) {
		this.typeT = typeT;
	}

	public T findByParameterObjectTwo(String parameter, String parameterTwo,
			Object valor, Object valorTwo) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<T> criteria = cb.createQuery(this.typeT);
		Root<T> object = criteria.from(this.typeT);
		criteria.select(object).where(cb.equal(object.get(parameter), valor),
				cb.equal(object.get(parameterTwo), valorTwo));
		return em.createQuery(criteria).getSingleResult();
	}
}