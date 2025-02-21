package com.betacom.backend.services.implementations.products;

import java.io.Console;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betacom.backend.dto.products.ProductDescriptionDTO;
import com.betacom.backend.model.products.Product;
import com.betacom.backend.model.products.ProductDescription;
import com.betacom.backend.repositories.products.IProductDescriptionRepository;
import com.betacom.backend.repositories.products.IProductRepository;
import com.betacom.backend.request.products.ProductDescriptionRequest;
import com.betacom.backend.services.interfaces.messages.MessageServices;
import com.betacom.backend.services.interfaces.products.ProductDescriptionServices;

@Service
public class ProductDescriptionImpl implements ProductDescriptionServices {

	@Autowired
	IProductDescriptionRepository PrDesRepo;
	
	@Autowired
	IProductRepository prodRep;
	
	@Autowired
	MessageServices msgS;
	
	@Override
	public ProductDescriptionDTO getDescription(Long Idprodotto,String lang)throws Exception {
		ProductDescription productDes= PrDesRepo.findByProductIdAndLang(Idprodotto ,lang).orElseThrow(
				()->
			 new Exception(msgS.getMessage("missing-attributes")
		)) ;
		return new ProductDescriptionDTO(productDes.getId(), lang,Idprodotto,productDes.getDescription());

	
		
	}
	@Override
	public ProductDescriptionDTO getDescription(Long id) throws Exception {
		ProductDescription productDes= PrDesRepo.findById(id).orElseThrow(
				()->
			 new Exception(msgS.getMessage("missing-id")
		)) ;
		return new ProductDescriptionDTO(id, productDes.getLang(),productDes.getProduct().getId() , productDes.getDescription());
	}


	@Override
	public void createDescription(ProductDescriptionRequest req)throws Exception {

		
		Optional<Product>prodotto=prodRep.findById(req.getIdprodotto());
		if (prodotto.isEmpty())new Exception(msgS.getMessage("missing-id"));
		
		
		
		if (mancanoAttributi(req)) new Exception(msgS.getMessage("missing-attributes"));
		
		
		ProductDescription descrizione=new ProductDescription(req);
		descrizione.setProduct(prodotto.get());
		PrDesRepo.save(descrizione);
		
		
	}
	
	@Override
	public void updateDescription(ProductDescriptionRequest req) throws Exception {
	    if (mancanoAttributi(req)) {
	        throw new Exception(msgS.getMessage("missing-attributes"));
	    }

	    Product prodotto = prodRep.findById(req.getIdprodotto())
	            .orElseThrow(() -> new Exception(msgS.getMessage("missing-id")));

	    ProductDescription desc = PrDesRepo.findById(req.getId())
	            .orElseThrow(() -> new Exception(msgS.getMessage("missing-id")));

	    desc.setProduct(prodotto);
	    if (req.getDescription()!=null) {
	    	desc.setDescription(req.getDescription());
		} 
	    if (req.getLang()!=null) {
	    	desc.setLang(req.getLang());
		} 
	 
	    
	}

	@Override
	public void deleteDescription(ProductDescriptionRequest req) throws Exception {
		ProductDescription description=PrDesRepo.findById(req.getId()).orElseThrow(
				()->
			 new Exception(msgS.getMessage("missing-id")
		)) ;
		PrDesRepo.delete(description);
		
	}
	private boolean mancanoAttributi(ProductDescriptionRequest req) {
        return  req.getDescription() == null || req.getDescription().isBlank()
                || req.getLang() == null || req.getLang().isBlank()
                || req.getIdprodotto()== null
                ;
                

    }

	

	

}
