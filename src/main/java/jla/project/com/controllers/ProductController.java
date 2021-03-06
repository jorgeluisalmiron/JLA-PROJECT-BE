package jla.project.com.controllers;

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
import jla.project.com.model.Product;
import jla.project.com.services.interfaces.ProductService;
import jla.project.com.utils.ResponseMessage;
import jla.project.com.utils.Search;
import jla.project.com.utils.CustomException;

@CrossOrigin(origins = "*")
@Controller
@RequestMapping(value = "/api/products")
public class ProductController {

	@Autowired
	private ProductService service;
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Product>> findAll() throws Exception {
		try {

			List<Product> list = service.findAll();
			return new ResponseEntity<>(list, HttpStatus.OK);

		} catch (Exception e) {
			logger.error(e.toString());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Object> create(@RequestBody Product product)  {

		try {
			Product createdProduct = service.create(product);
			return ResponseEntity.status(HttpStatus.OK).body(createdProduct);

		} catch (CustomException e) {
			logger.error(e.toString());
			switch (e.getExceptionId()) {
			case 1:
					return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new ResponseMessage(1,e.getMessage()));
			default:
					return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage(500,e.getMessage()));
		
			}
			
		} catch (Exception e) {
			logger.error(e.toString());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage(500,e.getMessage()));
	
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> delete(@PathVariable long id)  {

		try {
			service.delete(id);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(0,"Success"));
		} catch (Exception e) {
			logger.error(e.toString());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage(500,e.getMessage()));
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> findOne(@PathVariable long id) throws Exception {

		try {
			Product product = service.findById(id);
			return ResponseEntity.status(HttpStatus.OK).body(product);
		} catch (CustomException e) {
			logger.error(e.toString());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage(500,e.getMessage()));
			
		}
	}		

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Object> update(@RequestBody Product product) {
		try {

			service.update(product);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(1,"Success"));
		} catch (Exception e) {
			logger.error(e.toString());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage(500,e.getMessage()));
		}
	}

	
	 @RequestMapping(method = RequestMethod.POST, value = "/search")
	 public ResponseEntity<Object> search(@RequestBody Search search) {
		Page<Product> list;
		try {
			list = service.findBySearchPaginable(search);
			return ResponseEntity.status(HttpStatus.OK).body(list);
		} catch (CustomException e) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new ResponseMessage(1,e.getMessage()));

		} catch (Exception e) {
			logger.error(e.toString());
		 	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage(500,e.getMessage()));
		}
	 }
	 
}
