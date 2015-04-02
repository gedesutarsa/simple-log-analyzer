package id.co.rwg.tools.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import id.co.rwg.tools.dao.IGeneralPurposeDao;

/**
 * membaca data
 * @author <a href='mailto:gede.sutarsa@gmail.com'>Gede Sutarsa</a>
 */
@Repository
public class GeneralPurposeDaoImpl implements IGeneralPurposeDao{

	
	

	/**
	 * entity manager
	 **/
	@PersistenceContext
	private EntityManager entityManager; 
	
	
	
	@Override
	public <DATA> DATA insert(DATA data) throws Exception {
		entityManager.persist(data);
		return data;
	}

	@Override
	public <DATA> void update(DATA data) throws Exception {
		entityManager.merge(data); 
		
	}

	@Override
	public <DATA> void delete(DATA data) throws Exception {
		entityManager.remove(data);
		
	}

}
