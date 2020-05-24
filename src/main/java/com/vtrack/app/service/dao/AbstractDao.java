package com.vtrack.app.service.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author jayant
 *
 * @param <PK>
 * @param <T>
 */
public class AbstractDao<PK extends Serializable, T> {

	@SuppressWarnings("unused")
	private Class<T> presistanceClass;
	
	@SuppressWarnings("unchecked")
	public AbstractDao(){
		this.presistanceClass = 
				(Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
	}
		@Autowired
		private EntityManager entityManager;
		
		public Session getSession() {
			return (Session) entityManager.getDelegate();
		}
		
	
}