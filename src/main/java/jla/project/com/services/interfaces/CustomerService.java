package jla.project.com.services.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;
import jla.project.com.model.Customer;
import jla.project.com.utils.Search;


public interface CustomerService {
	
	Customer create(Customer customer) throws Exception;
	void update(Customer customer) throws Exception;
	List<Customer> findAll () throws Exception;
	Customer findById(Long id) throws Exception;
	void delete(Long id) throws Exception;
	Page<Customer> findBySearchPaginable(Search search) throws Exception;
}
