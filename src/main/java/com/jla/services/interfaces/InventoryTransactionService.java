package com.jla.services.interfaces;

import java.util.List;
import org.springframework.data.domain.Page;
import com.jla.model.InventoryTransaction;
import com.jla.utils.Search;


public interface InventoryTransactionService {
	
	public InventoryTransaction create(InventoryTransaction product) throws Exception;
	public void update(InventoryTransaction product) throws Exception;
	public List<InventoryTransaction> findAll () throws Exception;
	public InventoryTransaction findById(Long id) throws Exception;
	public void delete(Long id) throws Exception;
	public Page<InventoryTransaction> findBySearchPaginable(Search search) throws Exception;
	Page<InventoryTransaction> findAllByPagination(int page, int size) throws Exception;
	public void process(Long id) throws Exception;
	public Page<InventoryTransaction> findAllOnlyHeaderPaginable (Search search) throws Exception;
	
	

}
