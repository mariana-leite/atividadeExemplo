package atividadeExemplo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class GenericDao<T> {
	
	protected EntityManager entityManager;
	
	public EntityManager getEntityManager() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("atividadeExemplo");
		if (entityManager == null)
			entityManager = factory.createEntityManager();
		return entityManager;
	}
	
	public void criar(T obj) {
		try {
			entityManager = getEntityManager();
			entityManager.getTransaction().begin();
	        entityManager.persist(obj);
	        entityManager.getTransaction().commit();
	        entityManager.close();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			throw e;
		}
		
	}
	
	public void atualizar(T obj) {
		try {
			entityManager = getEntityManager();
			entityManager.getTransaction().begin();
	        entityManager.merge(obj);
	        entityManager.getTransaction().commit();
	        entityManager.close();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			throw e;
		}
	}
	
	
	public void excluir(T obj) {
		try {
			entityManager = getEntityManager();
			entityManager.getTransaction().begin();
			entityManager.remove(entityManager.contains(obj) ? obj : entityManager.merge(obj));
	        entityManager.getTransaction().commit();
	        entityManager.close();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			throw e;
		}
	}
	
	public T findByPrimaryKey(Class<T> c, int id) {
		entityManager = getEntityManager();
		T resultado = entityManager.find(c, id);
		return resultado;
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findAll(Class<T> c) {
		entityManager = getEntityManager();
		List<T> resultado = entityManager.createQuery(" from " + c.getName()).getResultList();
		return resultado;
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findByField(Class<T> c, String field, Object value){
		entityManager = getEntityManager();
		if(value.getClass().equals(String.class) || value.getClass().equals(char.class)) {
			value = "'" + value + "'";
		}
		List<T> resultado = entityManager.createQuery(" from " + c.getName() + " where "
				+ field + " = " + value.toString() + " order by id ").getResultList();
		return resultado;
	}
}
