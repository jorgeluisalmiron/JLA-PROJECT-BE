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
import jla.project.com.model.InventoryTransaction;
import jla.project.com.services.interfaces.InventoryTransactionService;
import jla.project.com.utils.ResponseMessage;
import jla.project.com.utils.Search;
import jla.project.com.utils.CustomException;

@CrossOrigin(origins = "*")
@Controller
@RequestMapping(value = "/api/inventoryTransactions")
public class InventoryTransactionsController {

	@Autowired
	private InventoryTransactionService service;
	private static final Logger logger = LoggerFactory.getLogger(InventoryTransactionsController.class);

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<InventoryTransaction>> findAll() throws Exception {
		try {

			List<InventoryTransaction> list = service.findAll();
			return new ResponseEntity<List<InventoryTransaction>>(list, HttpStatus.OK);

		} catch (Exception e) {

			logger.info(e.toString());

			return new ResponseEntity<List<InventoryTransaction>>(HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}
	
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Object> create(@RequestBody InventoryTransaction inventoryTransaction) throws Exception {

		try {
			InventoryTransaction createdInventoryTransaction = service.create(inventoryTransaction);
			return ResponseEntity.status(HttpStatus.OK).body(createdInventoryTransaction);

		} catch (CustomException e) {

			switch (e.getExceptionId()) {
			case 1:

				return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
						.body(new ResponseMessage(1, e.getMessage()));
			default:
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body(new ResponseMessage(500, e.getMessage()));

			}

		} catch (Exception e) {

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseMessage(500, e.getMessage()));

		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> delete(@PathVariable long id) throws Exception {

		try {
			service.delete(id);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(0, "Success"));
		} catch (Exception e) {

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseMessage(500, e.getMessage()));

		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> findOne(@PathVariable long id) throws Exception {

		try {
			InventoryTransaction inventoryTransaction = service.findById(id);
			return ResponseEntity.status(HttpStatus.OK).body(inventoryTransaction);
		} catch (CustomException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseMessage(500, e.getMessage()));

		}
	}

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Object> update(@RequestBody InventoryTransaction inventoryTransaction) {
		try {

			service.update(inventoryTransaction);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(1, "Success"));
		} catch (Exception e) {

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseMessage(500, e.getMessage()));
		}
	}

	@RequestMapping(value = "/process:{id}", method = RequestMethod.PUT)
	public ResponseEntity<Object> process(@PathVariable long id) throws Exception {
		try {

			service.process(id);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(1, "Success"));
		}

		catch (CustomException e) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new ResponseMessage(1, e.getMessage()));

		} catch (Exception e) {

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseMessage(500, e.getMessage()));
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/search")
	public ResponseEntity<Object> search(@RequestBody Search search) {
		Page<InventoryTransaction> list;
		try {
			list = service.findBySearchPaginable(search);
			return ResponseEntity.status(HttpStatus.OK).body(list);

		} catch (CustomException e) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new ResponseMessage(1, e.getMessage()));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseMessage(500, e.getMessage()));

		}

	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/search/headers")
	public ResponseEntity<Object> searchOnlyHeaders(@RequestBody Search search) {
		Page<InventoryTransaction> list;
		try {
			list = service.findAllOnlyHeaderPaginable(search);
			return ResponseEntity.status(HttpStatus.OK).body(list);

		} catch (CustomException e) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new ResponseMessage(1, e.getMessage()));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseMessage(500, e.getMessage()));

		}

	}




} // class CompanyController
