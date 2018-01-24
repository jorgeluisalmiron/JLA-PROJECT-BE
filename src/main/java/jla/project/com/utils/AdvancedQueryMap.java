package jla.project.com.utils;

public class AdvancedQueryMap {
	
	private String field;
	private String value;
	private String comparator;
	private Class<?> dataType;
	private Object o;
	
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getComparator() {
		return comparator;
	}
	public void setComparator(String comparator) {
		this.comparator = comparator;
	}
	public Class<?> getDataType() {
		return dataType;
	}
	public void setDataType(Class<?> dataType) {
		this.dataType = dataType;
	}
	public Object getO() {
		return o;
	}
	public void setO(Object o) {
		this.o = o;
	}
	
	
	public AdvancedQueryMap() {
		super();
	}
	public AdvancedQueryMap(String field, String value, String comparator) {
		super();
		this.field = field;
		this.value = value;
		this.comparator = comparator;
	}
	
	

}
