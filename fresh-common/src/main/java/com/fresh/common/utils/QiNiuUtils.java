package com.fresh.common.utils;

import java.util.Collection;

public class QiNiuUtils {
	 public static String join(Object[] array, String sep) {
	        return join(array, sep, null);
	    }
	 public static String join(Collection list, String sep) {
	        return join(list, sep, null);
	    }
	 
	 public static String join(Collection list, String sep, String prefix) {
	        Object[] array = list == null ? null : list.toArray();
	        return join(array, sep, prefix);
	    }
	
	   public static String join(Object[] array, String sep, String prefix) {
	        if (array == null) {
	            return "";
	        }

	        int arraySize = array.length;

	        if (arraySize == 0) {
	            return "";
	        }

	        if (sep == null) {
	            sep = "";
	        }

	        if (prefix == null) {
	            prefix = "";
	        }

	        StringBuilder buf = new StringBuilder(prefix);
	        for (int i = 0; i < arraySize; i++) {
	            if (i > 0) {
	                buf.append(sep);
	            }
	            buf.append(array[i] == null ? "" : array[i]);
	        }
	        return buf.toString();
	    }
}
