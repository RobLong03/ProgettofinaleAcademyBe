package com.betacom.backend.services.interfaces.products;


import java.util.List;

import com.betacom.backend.dto.products.ProductDTO;
import com.betacom.backend.request.products.ProductRequest;

public interface ProductServices {
    List<ProductDTO> list(String lang) throws Exception;        //list per fare list di tutto
    
    List<ProductDTO> filteredList(List<String> types, Double minPrice, Double maxPrice, List<String> brands, String lang) throws Exception;        //list con filtri su tipo, prezzo(min e max) e marca

    ProductDTO get(Long id,String lang) throws Exception;         //get per prendere con id,

    //poi altri get o list con specifiche diverse si decide dopo

    String create(ProductRequest req ) throws Exception;

    void update(ProductRequest req) throws Exception;

    void delete(Long id) throws Exception ;

}