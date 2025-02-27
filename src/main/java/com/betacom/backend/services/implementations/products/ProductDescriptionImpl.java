package com.betacom.backend.services.implementations.products;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
		ProductDescription productDes= PrDesRepo.findByProductIdAndLang(Idprodotto ,lang).
				orElse(null);
		if (productDes==null) return null;
			
	
		
		return new ProductDescriptionDTO(productDes.getId(), lang,Idprodotto,productDes.getDescription());

	
		//da risolvere per molti
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
	public List<ProductDescriptionDTO> getDescriptions(Long id) throws Exception {
	    
	    List<Optional<ProductDescription>> productDescriptionOptionals = PrDesRepo.findByProductId(id);
	  
	    if (productDescriptionOptionals.isEmpty()) {
	        return null; 
	    }

	    List<ProductDescriptionDTO> dtoList = productDescriptionOptionals.stream()
	        .filter(Optional::isPresent)
	        .map(Optional::get)
	        .map(pd -> new ProductDescriptionDTO(
	                pd.getId(), 
	                pd.getLang(), 
	                pd.getProduct().getId(), 
	                pd.getDescription()))
	        .collect(Collectors.toList());
	    
	    if (dtoList.isEmpty()) {
	        throw new Exception(msgS.getMessage("missing-id"));
	    }
	    
	    return dtoList;
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
	@Override
	public void truncateAllDescription(ProductDescriptionRequest req) throws Exception {
		
		PrDesRepo.deleteAllByProductId(req.getIdprodotto());
	}
	private boolean mancanoAttributi(ProductDescriptionRequest req) {
        return  req.getDescription() == null || req.getDescription().isBlank()
                || req.getLang() == null || req.getLang().isBlank()
                || req.getIdprodotto()== null
                ;
                

    }
	

	

	

}
