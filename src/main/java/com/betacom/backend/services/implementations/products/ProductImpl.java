package com.betacom.backend.services.implementations.products;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betacom.backend.dto.products.ProductDTO;
import com.betacom.backend.model.products.Product;
import com.betacom.backend.repositories.products.IProductRepository;
import com.betacom.backend.request.products.ProductRequest;
import com.betacom.backend.services.interfaces.products.ProductServices;


@Service
public class ProductImpl implements ProductServices {

    @Autowired
    IProductRepository prodRep;

    @Override
    public List<ProductDTO> list() {
        List<Product> lProd = prodRep.findAll();

            return lProd.stream().map(p ->
                    new ProductDTO(p)
            ).collect(Collectors.toList());
    }

    @Override
    public ProductDTO get(Long id) throws Exception{
        if(id == null){
            throw new Exception("missing-id");
        }

        Optional<Product> prod = prodRep.findById(id);

        if(prod.isPresent()){
            return new ProductDTO(prod.get());
        }else{
            throw new Exception("not-found");
        }
    }

    @Override
    public void create(ProductRequest req) throws Exception {
        if(mancanoAttributi(req))
            throw new Exception("missing-attributes");

        Product p = new Product(req );
         prodRep.save(p);
    }



    @Override
    public void update(ProductRequest req) throws Exception {
        if(req.getId() == null){
            throw new Exception("missing-id");
        }

        if( prodRep.findById(req.getId()).isEmpty()){
            throw new Exception("does-not-exists");
        }

        Product p = new Product(req);

        prodRep.save(p);
    }

    @Override
    public void delete(Long id) throws Exception {
        if(id == null){
            throw new Exception("missing-id");
        }

        prodRep.deleteById(id);

    }

    private boolean mancanoAttributi(ProductRequest req) {
        return req.getDescription() == null || req.getDescription().isBlank()
                || req.getBrand() == null || req.getBrand().isBlank()
                || req.getModel() == null || req.getModel().isBlank()
                || req.getStock() == null;

    }
}
