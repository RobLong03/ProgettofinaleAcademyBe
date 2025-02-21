package com.betacom.backend.services.interfaces.products;

import com.betacom.backend.dto.products.ProductDescriptionDTO;
import com.betacom.backend.request.products.ProductDescriptionRequest;

public interface ProductDescriptionServices {

	ProductDescriptionDTO getDescription(Long Idprodotto,String lang)throws Exception;
	
	ProductDescriptionDTO getDescription(Long id)throws Exception;
	
	void createDescription(ProductDescriptionRequest req)throws Exception; 
	
	void updateDescription(ProductDescriptionRequest req)throws Exception; 
	
	void deleteDescription(ProductDescriptionRequest req)throws Exception; 
}
