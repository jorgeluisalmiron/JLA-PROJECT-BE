package jla.project.com.services;

import java.util.ArrayList;
import java.util.List;

import jla.project.com.services.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import jla.project.com.dao.ProductDao;
import jla.project.com.model.Product;
import jla.project.com.utils.CustomException;
import jla.project.com.utils.CustomSpecification;
import jla.project.com.utils.Search;



@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductDao dao;

	@Override
	public Product create(Product product) throws Exception {
		if (dao.findByCode(product.getCode()) != null) {
			throw new CustomException(1,"This product already exists!");
		} 
		return dao.save(product);
	}

	@Override
	public void update(Product product) throws Exception {

		dao.save(product);

	}

	@Override
	public List<Product> findAll() throws Exception {

		List<Product> list = new ArrayList<>();
		dao.findAll().forEach(list::add);
		return list;

	}

	@Override
	public Product findById(Long id) throws Exception {

		return dao.findOne(id);
	}

	@Override
	public void delete(Long id) throws Exception {
		dao.delete(id);
	}

	@Override
	public Page<Product> findAllByPagination(int page, int size) throws Exception {

		try {
			Pageable pageable = new PageRequest(page, size);
			Page<Product> results = dao.findAll(pageable);
			return results;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Page<Product> findBySearchPaginable(Search search) throws Exception {

		Pageable pageable;
		Page<Product> results;
		try {
			if (search != null) {

				if (search.getSortByField().isEmpty()) {
					pageable = new PageRequest(search.getPage(), search.getSize());

				} else {
					pageable = new PageRequest(search.getPage(), search.getSize(),
							new Sort(search.getSortDirection(), search.getSortByField()));
				}

				if (!search.getSearchCriteria().isEmpty()) {
					CustomSpecification<Product> spec = new CustomSpecification<Product>(
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
