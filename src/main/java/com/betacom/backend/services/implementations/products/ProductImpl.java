package com.betacom.backend.services.implementations.products;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betacom.backend.dto.products.ProductDTO;
import com.betacom.backend.dto.products.ProductDescriptionDTO;
import com.betacom.backend.model.products.Product;
import com.betacom.backend.repositories.products.IProductRepository;
import com.betacom.backend.request.products.ProductRequest;
import com.betacom.backend.services.interfaces.messages.MessageServices;
import com.betacom.backend.services.interfaces.products.ProductDescriptionServices;
import com.betacom.backend.services.interfaces.products.ProductServices;


@Service
public class ProductImpl implements ProductServices {

    @Autowired
    Logger log;

    @Autowired
    MessageServices msgS;

    @Autowired
    IProductRepository prodRep;
    
    @Autowired
    ProductDescriptionServices pdescS;
    
    
    

    @Override
    public List<ProductDTO> list(String lang) throws Exception {
        log.debug("PI: list");
       

        List<Product> lProd = prodRep.findAll();

        return lProd.stream()
                .map(p -> {
                    ProductDTO dto = new ProductDTO(p);
                   
                    try {
                        dto.setDescription(pdescS.getDescription(p.getId(), lang));
                    } catch (Exception e) {
                    	return dto;
                    }
                    return dto;
                })
                .collect(Collectors.toList());
    }

 
    @Override
    public ProductDTO get(Long id,String lang) throws Exception{
        log.debug("PI: get request with id:"+id);
        if(id == null){
            log.error("PI: id is null");
            throw new Exception(msgS.getMessage("missing-id-get"));
        }

        Optional<Product> prod = prodRep.findById(id);

        if(prod.isPresent()){
        	ProductDTO product = new ProductDTO(prod.get());
        	 ProductDescriptionDTO description = pdescS.getDescription(product.getId(), lang);
        	if (description != null)  product.setDescription(description);
        	
        /*	
        if () {
        		product.setDescription();
			}
        */
        
            return product;
        }else{
            log.error("missing product");
            throw new Exception(msgS.getMessage("does-not-exist-get"));
        }
    }
//creating of description is distincted with other class ProductDescriptionImpl
    @Override
    public void create(ProductRequest req) throws Exception {
        log.debug("PI: create request:"+req);

        if(mancanoAttributi(req)) {
            log.error("PI: missing attributes");
            throw new Exception(msgS.getMessage("missing-attributes-create"));
        }

        Product p = new Product(req);
         prodRep.save(p);
    }



    @Override
    public void update(ProductRequest req) throws Exception {
        if(req.getId() == null){
            throw new Exception(msgS.getMessage("missing-id-update"));
        }

        if( prodRep.findById(req.getId()).isEmpty()){
            throw new Exception(msgS.getMessage("does-not-exist-update"));
        }

        Product p = new Product(req);

        prodRep.save(p);
    }

    @Override
    public void delete(Long id) throws Exception {
        if(id == null){
            throw new Exception(msgS.getMessage("missing-id-delete"));
        }

        prodRep.deleteById(id);

    }

    private boolean mancanoAttributi(ProductRequest req) {
        return  req.getBrand() == null || req.getBrand().isBlank()
                || req.getModel() == null || req.getModel().isBlank()
                || req.getPrice() == null
                || req.getStock() == null;

    }
}
