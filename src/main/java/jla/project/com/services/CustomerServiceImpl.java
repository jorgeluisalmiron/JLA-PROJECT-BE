package jla.project.com.services;

import java.util.ArrayList;
import java.util.List;

import jla.project.com.services.interfaces.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import jla.project.com.dao.CustomerDao;
import jla.project.com.model.Customer;
import jla.project.com.utils.CustomException;
import jla.project.com.utils.CustomSpecification;
import jla.project.com.utils.Search;



@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerDao dao;

	@Override
	public Customer create(Customer customer) throws Exception {
		if (dao.findByIdentificationNum(customer.getIdentificationNum()) != null) {
			throw new CustomException(1,"Custmer identified by entered taxnumber already exists!.");
		} 
		return dao.save(customer);
	}

	@Override
	public void update(Customer customer) throws Exception {
		dao.save(customer);
	}

	@Override
	public List<Customer> findAll() throws Exception {
		List<Customer> list = new ArrayList<>();
		dao.findAll().forEach(list::add);
		return list;
	}

	@Override
	public Customer findById(Long id) throws Exception {
		return dao.findOne(id);
	}

	@Override
	public void delete(Long id) throws Exception {
		dao.delete(id);
	}


	@Override
	public Page<Customer> findBySearchPaginable(Search search) throws Exception {

		Pageable pageable;
		Page<Customer> results;
		try {
			if (search != null) {

				if (search.getSortByField().isEmpty()) {
					pageable = new PageRequest(search.getPage(), search.getSize());

				} else {
					pageable = new PageRequest(search.getPage(), search.getSize(),
							new Sort(search.getSortDirection(), search.getSortByField()));
				}

				if (!search.getSearchCriteria().isEmpty()) {
					CustomSpecification<Customer> spec = new CustomSpecification<Customer>(
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
}
