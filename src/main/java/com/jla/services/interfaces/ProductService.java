package com.jla.services.interfaces;

import java.util.List;
import org.springframework.data.domain.Page;
import com.jla.model.Product;
import com.jla.utils.Search;


public interface ProductService {
	
	Product create(Product product) throws Exception;
	void update(Product product) throws Exception;
	List<Product> findAll () throws Exception;
	Product findById(Long id) throws Exception;
	void delete(Long id) throws Exception;
	Page<Product> findBySearchPaginable(Search search) throws Exception;
	Page<Product> findAllByPagination(int page, int size) throws Exception;
	
	

}
