package jla.project.com.utils;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

public class AdvancedQueryUtilities {

	public static <T> Class<?> getDataType(Class<T> classEntity, String field)
	{
		for (java.lang.reflect.Field f : classEntity.getDeclaredFields()) {
		    Class<?> clazz = f.getType();
		    
		    if (f.getName().equals(field))
		    {
		    	return clazz;
		    }
	 
		}
		return null;
	}
	
	public static <T> boolean fieldIsInClass(Class<T> classEntity, String field)
	{
		for (java.lang.reflect.Field f : classEntity.getDeclaredFields()) {
		   	    
		    if (f.getName().equals(field))
		    {
		    	return true;
		    }
	 
		}
		return false;
	}
	
	public static Object getValue(String value, Class<?> clazz )
	{
		
		
		 if ( clazz.getName().equals("long")) {
					
	    	 long _long = Long.parseLong(value);
	         return _long;  
	     }
		 if (clazz.equals(String.class)) {
			
			 String _String = value;
	         return _String;  
	     }
		 if ( clazz.getName().equals("int")) {
			 int _int = Integer.parseInt(value);
	         return _int;  
	     }
		 if ( clazz.getName().equals("boolean")) {
			 boolean _boolean = Boolean.parseBoolean(value);
	         return _boolean;  
	     }
		 if ( clazz.getName().equals("short")) {
			 short _short = Short.parseShort(value);
	         return _short;  
	     }
		 if ( clazz.getName().equals("double")) {
			 double _double = Double.parseDouble(value);
	         return _double;  
	     }
		 if ( clazz.getName().equals("float")) {
			 double _float = Float.parseFloat(value);
	         return _float;  
	     }
		 if ( clazz.getName().equals("char")) {
			 char _char = value.charAt(0);
	         return _char;  
	     }
		 
	     return null;
		
		
	}
	public static Criterion createRestrictions (AdvancedQueryMap queryMap)
	{
		
				 
		switch (queryMap.getComparator())
		{
		case "=":
			return Restrictions.eq(queryMap.getField(), getValue(queryMap.getValue(),queryMap.getDataType()));
		case ">":
			return Restrictions.gt(queryMap.getField(), getValue(queryMap.getValue(),queryMap.getDataType()));
			
		case "<":
			return Restrictions.lt(queryMap.getField(), getValue(queryMap.getValue(),queryMap.getDataType()));
		
		case ">=":
			
			return Restrictions.ge(queryMap.getField(), getValue(queryMap.getValue(),queryMap.getDataType()));
		case "<=":
			return Restrictions.le(queryMap.getField(), getValue(queryMap.getValue(),queryMap.getDataType()));

		case "like":
			return Restrictions.like(queryMap.getField(), getValue(queryMap.getValue(),queryMap.getDataType()));
		
		case "<>":
			return Restrictions.ne(queryMap.getField(), getValue(queryMap.getValue(),queryMap.getDataType()));
		
		case "isNull":
			return (SimpleExpression) Restrictions.isNull(queryMap.getField());	
		
		case "isNotNull":
			return (SimpleExpression) Restrictions.isNotNull(queryMap.getField());		
			
		default:
			return null;
		
		}
		
	
		
	}
}
