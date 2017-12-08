package com.jla.services.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;
import com.jla.model.Customer;
import com.jla.utils.Search;


public interface CustomerService {
	
	public Customer create(Customer customer) throws Exception;
	public void update(Customer customer) throws Exception;
	public List<Customer> findAll () throws Exception;
	public Customer findById(Long id) throws Exception;
	public void delete(Long id) throws Exception;
	public Page<Customer> findBySearchPaginable(Search search) throws Exception;
	Page<Customer> findAllByPagination(int page, int size) throws Exception;
	
	

}
