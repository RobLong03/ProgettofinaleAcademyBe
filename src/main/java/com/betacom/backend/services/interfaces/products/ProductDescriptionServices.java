package com.betacom.backend.services.interfaces.products;

import java.util.List;

import com.betacom.backend.dto.products.ProductDescriptionDTO;
import com.betacom.backend.request.products.ProductDescriptionRequest;

public interface ProductDescriptionServices {

	ProductDescriptionDTO getDescription(Long Idprodotto,String lang)throws Exception;
	
	List<ProductDescriptionDTO> getDescriptions(Long id) throws Exception; 
	
	ProductDescriptionDTO getDescription(Long id)throws Exception;
	
	void createDescription(ProductDescriptionRequest req)throws Exception; 
	
	void updateDescription(ProductDescriptionRequest req)throws Exception; 
	
	void deleteDescription(ProductDescriptionRequest req)throws Exception;
	
	void truncateAllDescription(ProductDescriptionRequest req)throws Exception;

	
}
