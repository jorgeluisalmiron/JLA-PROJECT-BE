package jla.project.com.services.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;
import jla.project.com.model.Supplier;
import jla.project.com.utils.Search;


public interface SupplierService {
	
	Supplier create(Supplier supplier) throws Exception;
	void update(Supplier supplier) throws Exception;
	List<Supplier> findAll () throws Exception;
	Supplier findById(Long id) throws Exception;
	void delete(Long id) throws Exception;
	Page<Supplier> findBySearchPaginable(Search search) throws Exception;

}
