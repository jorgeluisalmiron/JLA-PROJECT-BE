package com.jla.utils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class CustomSpecification<T>  implements Specification<T> {
	 
    private SearchCriteria criteria;
 
    

	public CustomSpecification(SearchCriteria searchCriteria) {
		this.criteria=searchCriteria;
	}



	@Override
	public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		
		 
		switch (criteria.getOperation())
		{
		case "=":
			 return builder.equal(root.get(criteria.getKey()), criteria.getValue());
			
		case ">":
			
			return builder.greaterThan( root.<String> get(criteria.getKey()), criteria.getValue().toString());
		
			
		case "<":
			return builder.lessThan( root.<String> get(criteria.getKey()), criteria.getValue().toString());
		
		case ">=":
			
			  return builder.greaterThanOrEqualTo(root.<String> get(criteria.getKey()), criteria.getValue().toString());
		case "<=":
			  return builder.lessThanOrEqualTo(root.<String> get(criteria.getKey()), criteria.getValue().toString());

		case ":":
			 if (root.get(criteria.getKey()).getJavaType() == String.class) {
	                return builder.like(
	                  root.<String>get(criteria.getKey()), "%" + criteria.getValue() + "%");
	            } else {
	                return builder.equal(root.get(criteria.getKey()), criteria.getValue());
	            }
		
		case "<>":
			 return builder.notEqual(root.get(criteria.getKey()), criteria.getValue());
		
		case "isNull":
			return builder.isEmpty(root.get(criteria.getKey()));
		
		case "isNotNull":
			return builder.isNotEmpty(root.get(criteria.getKey()));		
			
		default:
			return null;
		
		}
		
		
	
	}
}