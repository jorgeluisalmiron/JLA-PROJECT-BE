package com.jla.services.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;
import com.jla.model.Supplier;
import com.jla.utils.Search;


public interface SupplierService {
	
	public Supplier create(Supplier supplier) throws Exception;
	public void update(Supplier supplier) throws Exception;
	public List<Supplier> findAll () throws Exception;
	public Supplier findById(Long id) throws Exception;
	public void delete(Long id) throws Exception;
	public Page<Supplier> findBySearchPaginable(Search search) throws Exception;
	Page<Supplier> findAllByPagination(int page, int size) throws Exception;
	
	

}
