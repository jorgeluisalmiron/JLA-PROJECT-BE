package com.jla.dao;



import javax.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jla.model.InventoryTransaction;



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

public interface InventoryTransactionDao extends JpaRepository<InventoryTransaction, Long> , JpaSpecificationExecutor<InventoryTransaction> {
	
//	@Query("select new InventoryTransaction (trx.id, trx.date, trx.type, trx.comments ,trx.status) from InventoryTransaction trx" )
	//@Query( value = "from InventoryTransaction trx  where trx.spec = ?1",countQuery ="select count(trx.id) from InventoryTransaction trx")
	@Query("select new InventoryTransaction (trx.id, trx.date, trx.type, trx.comments ,trx.status) from InventoryTransaction trx" )
	Page<InventoryTransaction> findOnlyHeader(@Param("spec") Specification<InventoryTransaction> spec, Pageable pageable );
	
	@Query("select new InventoryTransaction (trx.id, trx.date, trx.type, trx.comments ,trx.status) from InventoryTransaction trx" )
	Page<InventoryTransaction> findAllOnlyHeader(Pageable pageable );

} // class IntenvoryTransactionsDao
