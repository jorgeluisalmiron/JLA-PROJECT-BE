package com.jla.utils;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;


public class Search {

	private String sortByField;
	private String sortType;
	private int page;
	private int size;
	private List<SearchCriteria> searchCriteria;
	
	
	public Search() {
		super();
	}

	public Search(String sortByField, String sortType, int page, int size, List<SearchCriteria> searchCriteria) {
		super();
		this.sortByField = sortByField;
		this.sortType = sortType;
		this.page = page;
		this.size = size;
		this.searchCriteria = searchCriteria;
	}

	public String getSortByField() {
		return sortByField;
	}

	public void setSortByField(String sortByField) {
		this.sortByField = sortByField;
	}

	public String getSortType() {
		return sortType;
	}

	public void setSortType(String sortType) {
		this.sortType = sortType;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public List<SearchCriteria> getSearchCriteria() {
		return searchCriteria;
	}

	public void setSearchCriteria(List<SearchCriteria> searchCriteria) {
		this.searchCriteria = searchCriteria;
	}
	
	
	public Direction getSortDirection(){
	
		if (this.sortType.equals("DESC"))
		{
			return Sort.Direction.DESC;
		}
		else
		{
			return  Sort.Direction.ASC;
		}
		
	
			
	}
	
	
	
	
	public static class SearchException extends Exception {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private int exceptionId;

		public SearchException(int exceptionId,String msg) {
			super(msg);
			this.setExceptionId(exceptionId);
		}

		public SearchException(String msg) {
			super(msg);
		}

		public int getExceptionId() {
			return exceptionId;
		}

		private void setExceptionId(int exceptionId) {
			this.exceptionId = exceptionId;
		}
	}
	
	
}
