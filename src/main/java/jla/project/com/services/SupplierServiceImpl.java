package jla.project.com.services;

import java.util.ArrayList;
import java.util.List;

import jla.project.com.services.interfaces.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import jla.project.com.dao.SupplierDao;
import jla.project.com.model.Supplier;
import jla.project.com.utils.CustomException;
import jla.project.com.utils.CustomSpecification;
import jla.project.com.utils.Search;

@Service
public class SupplierServiceImpl implements SupplierService {

	@Autowired
	SupplierDao dao;

	@Override
	public Supplier create(Supplier supplier) throws Exception {
		if (dao.findByIdentificationNum(supplier.getIdentificationNum()) != null) {
			throw new CustomException(1,"Custmer identified by entered taxnumber already exists!.");
		} 
		return dao.save(supplier);
	}

	@Override
	public void update(Supplier supplier) throws Exception {
		dao.save(supplier);
	}

	@Override
	public List<Supplier> findAll() throws Exception {
		List<Supplier> list = new ArrayList<>();
		dao.findAll().forEach(list::add);
		return list;
	}

	@Override
	public Supplier findById(Long id) throws Exception {

		return dao.findOne(id);
	}

	@Override
	public void delete(Long id) throws Exception {
		dao.delete(id);
	}

	@Override
	public Page<Supplier> findBySearchPaginable(Search search) throws Exception {

		Pageable pageable;
		Page<Supplier> results;
		try {
			if (search != null) {

				if (search.getSortByField().isEmpty()) {
					pageable = new PageRequest(search.getPage(), search.getSize());

				} else {
					pageable = new PageRequest(search.getPage(), search.getSize(),
							new Sort(search.getSortDirection(), search.getSortByField()));
				}

				if (!search.getSearchCriteria().isEmpty()) {
					CustomSpecification<Supplier> spec = new CustomSpecification<Supplier>(
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
