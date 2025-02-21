package com.betacom.backend.utils;

import com.betacom.backend.dto.products.ProductDTO;
import com.betacom.backend.model.products.Product;
import com.betacom.backend.request.products.ProductRequest;

final public class TypeConverter {

	final public static  String whichSubClassIs(Product product) {
	    if (product == null) {
	        return null; // Handle null case
	    }
	    
	    String temp=null;
	    temp=product.getClass().getSimpleName()
	    		.replaceAll("^[A-Z][a-z]*?[A-Z].*", "$1").toLowerCase();
	    temp=temp.contains("cases")?"case":temp;
	    return temp;
	}
	final public static  String whichSubClassIs(ProductDTO product) {
	    if (product == null) {
	        return null; // Handle null case
	    }
	    String temp=null;
	    temp=product.getClass().getSimpleName()
	    		.replaceAll("^[A-Z][a-z]*?[A-Z].*", "$1").toLowerCase();
	    temp=temp.contains("cases")?"case":temp;
	    return temp;
	
	}
	final public static  String whichSubClassIs(ProductRequest product) {
	    if (product == null) {
	        return null; // Handle null case
	    }
	    String temp=null;
	    temp=product.getClass().getSimpleName()
	    		.replaceAll("^[A-Z][a-z]*?[A-Z].*", "$1").toLowerCase();
	    temp=temp.contains("cases")?"case":temp;
	    return temp;
	
	}
}
