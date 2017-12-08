package com.jla.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.jla.dao.InventoryTransactionDao;
import com.jla.model.InventoryTransaction;
import com.jla.services.interfaces.InventoryTransactionService;
import com.jla.utils.CustomException;
import com.jla.utils.CustomSpecification;
import com.jla.utils.Search;


@Service
public class InventoryTransactionServiceImpl implements InventoryTransactionService {

	@Autowired
	InventoryTransactionDao dao;

	
	@Override
	public InventoryTransaction create(InventoryTransaction inventoryTransaction) throws Exception {
		
		inventoryTransaction.setStatus("Draft");
		return dao.save(inventoryTransaction);
	}

	@Override
	public void update(InventoryTransaction inventoryTransaction) throws Exception {
		
		InventoryTransaction trx=dao.findOne(inventoryTransaction.getId());
		if (trx!=null)
		{
			if(trx.getStatus()!="Draft")
				throw new CustomException(1,"You only can update transactions with status Draft"); 
			else
				dao.save(inventoryTransaction);
		}
		else
		{
			throw new CustomException(2,"Inventory transaction to update doesn´t exists");
		}
		
	}
	
	@Override
	public void process(Long id) throws Exception {
		
		InventoryTransaction inventoryTransaction=dao.findOne(id);
		if (inventoryTransaction!=null)
		{
			if(!inventoryTransaction.getStatus().equals("Draft"))
				throw new CustomException(1,"You only can process transactions with status Draft"); 
			else
				inventoryTransaction.setStatus("Processed");
				dao.save(inventoryTransaction);
		}
		else
		{
			throw new CustomException(2,"Inventory transaction to process doesn´t exists");
		}
		
	}

	@Override
	public List<InventoryTransaction> findAll() throws Exception {

		List<InventoryTransaction> list = new ArrayList<>();
		dao.findAll().forEach(list::add);
		return list;

	}

	@Override
	public InventoryTransaction findById(Long id) throws Exception {

		return dao.findOne(id);
	}
	@Override
	public void delete(Long id) throws Exception {
		InventoryTransaction trx=dao.findOne(id);
		if (trx!=null)
		{
			if(trx.getStatus()!="Draft")
				throw new CustomException(1,"Cannot delete a transaction with status distinct of draft");  //Error because cannot delete a transaction with status distinct of draft
			else
				dao.delete(id);
		}
		
	}

	@Override
	public Page<InventoryTransaction> findAllByPagination(int page, int size) throws Exception {

		try {
			Pageable pageable = new PageRequest(page, size);
			Page<InventoryTransaction> results = dao.findAll(pageable);
			return results;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Page<InventoryTransaction> findBySearchPaginable(Search search) throws Exception {

		Pageable pageable;
		Page<InventoryTransaction> results;
		try {
			if (search != null) {

				if (search.getSortByField().isEmpty()) {
					pageable = new PageRequest(search.getPage(), search.getSize());

				} else {
					pageable = new PageRequest(search.getPage(), search.getSize(),
							new Sort(search.getSortDirection(), search.getSortByField()));
				}

				if (!search.getSearchCriteria().isEmpty()) {
					CustomSpecification<InventoryTransaction> spec = new CustomSpecification<InventoryTransaction>(
							search.getSearchCriteria().get(0));
					results = dao.findAll(spec, pageable);
				} else {
					results = dao.findAll(pageable);
				}
				return results;
			} else {
				throw new CustomException(1, "Search Object is null");
			}

		} catch (Exception e) {
			throw e;
		}

	}

	
	public Page<InventoryTransaction> findAllOnlyHeaderPaginable (Search search) throws Exception
	{
		Pageable pageable;
		Page<InventoryTransaction> results;
		try {
			if (search != null) {

				if (search.getSortByField().isEmpty()) {
					pageable = new PageRequest(search.getPage(), search.getSize());

				} else {
					pageable = new PageRequest(search.getPage(), search.getSize(),
							new Sort(search.getSortDirection(), search.getSortByField()));
				}

				if (!search.getSearchCriteria().isEmpty()) {
					CustomSpecification<InventoryTransaction> spec = new CustomSpecification<InventoryTransaction>(
							search.getSearchCriteria().get(0));
					results = dao.findOnlyHeader(spec, pageable);
				} else {
					results = dao.findAllOnlyHeader(pageable);
				}
				return results;
			} else {
				throw new CustomException(1, "Search Object is null");
			}

		} catch (Exception e) {
			throw e;
		}
		
	}
	

	
	



}
