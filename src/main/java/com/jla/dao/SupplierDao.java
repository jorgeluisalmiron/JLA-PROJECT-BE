package com.jla.dao;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.jla.model.Supplier;



/**
 * A DAO for the entity User is simply created by extending the CrudRepository
 * interface provided by spring. The following methods are some of the ones
 * available from such interface: save, delete, deleteAll, findOne and findAll.
 * The magic is that such methods must not be implemented, and moreover it is
 * possible create new query methods working only by defining their signature!
 * 
 * @author jorge luis almiron
 */
@Transactional
public interface SupplierDao extends JpaRepository<Supplier, Long> , JpaSpecificationExecutor<Supplier>{

	public Supplier findByTaxNumber(String taxNumber);
	

} // class UserDao