package com.jla.controllers;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.jla.model.Customer;
import com.jla.services.interfaces.CustomerService;
import com.jla.utils.ResponseMessage;
import com.jla.utils.Search;
import com.jla.utils.CustomException;

@CrossOrigin(origins = "*")
@Controller
@RequestMapping(value = "/api/customers")
public class CustomerController {

	@Autowired
	private CustomerService service;
	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Customer>> findAll() throws Exception {
		try {

			List<Customer> list = service.findAll();
			return new ResponseEntity<List<Customer>>(list, HttpStatus.OK);

		} catch (Exception e) {

			logger.info(e.toString());

			return new ResponseEntity<List<Customer>>(HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Object> create(@RequestBody Customer customer) throws Exception {

		try {

			System.out.println(customer);
			Customer createdCustomer = service.create(customer);
			return ResponseEntity.status(HttpStatus.OK).body(createdCustomer);

		} catch (CustomException e) {

			switch (e.getExceptionId()) {
			case 1:
				
				return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new ResponseMessage(1,e.getMessage()));
			default:
				System.out.println("Custom exeption" + e);
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage(500,e.getMessage()));

		
			}
			
		} catch (Exception e) {

			System.out.println("General exeption" + e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage(500,e.getMessage()));
	
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> delete(@PathVariable long id) throws Exception {

		try {
			service.delete(id);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(0,"Success"));
		} catch (Exception e) {
	
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage(500,e.getMessage()));
			
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> findOne(@PathVariable long id) throws Exception {

		try {
			Customer customer = service.findById(id);
			return ResponseEntity.status(HttpStatus.OK).body(customer);
		} catch (CustomException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage(500,e.getMessage()));
			
		}
	}		

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Object> update(@RequestBody Customer customer) {
		try {

			service.update(customer);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(1,"Success"));
		} catch (Exception e) {

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage(500,e.getMessage()));
		}
	}

	
	 @RequestMapping(method = RequestMethod.POST, value = "/search")
	 public ResponseEntity<Object> search(@RequestBody Search search) {
		Page<Customer> list;
		try {
			list = service.findBySearchPaginable(search);
			return ResponseEntity.status(HttpStatus.OK).body(list);

		} catch (CustomException e) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new ResponseMessage(1,e.getMessage()));
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
		 	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage(500,e.getMessage()));

		}
			
	    
	    }
	 
} // class CompanyController
