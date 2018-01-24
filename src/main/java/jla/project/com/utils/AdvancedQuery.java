package jla.project.com.utils;

import java.util.List;


public class AdvancedQuery {
	
	private String sortByField;
	private String sortType;

	private List<AdvancedQueryMap> queryList;
	
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
	public List<AdvancedQueryMap> getQueryList() {
		return queryList;
	}
	public void setQueryList(List<AdvancedQueryMap> queryList) {
		this.queryList = queryList;
	}
	
	

}
