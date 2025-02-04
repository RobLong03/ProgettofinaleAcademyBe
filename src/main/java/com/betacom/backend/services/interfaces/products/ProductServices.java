package com.betacom.backend.services.interfaces.products;


import java.util.List;

import com.betacom.backend.dto.products.ProductDTO;
import com.betacom.backend.request.products.ProductRequest;

public interface ProductServices {
    List<ProductDTO> list();        //list per fare list di tutto

    ProductDTO get(Long id) throws Exception;         //get per prendere con id,

    //poi altri get o list con specifiche diverse si decide dopo

    void create(ProductRequest req ) throws Exception;

    void update(ProductRequest req) throws Exception;

    void delete(Long id) throws Exception ;

}