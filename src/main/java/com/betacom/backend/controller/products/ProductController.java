package com.betacom.backend.controller.products;


import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.backend.dto.products.ProductDTO;
import com.betacom.backend.request.products.ProductRequest;
import com.betacom.backend.response.ResponseBase;
import com.betacom.backend.response.ResponseList;
import com.betacom.backend.response.ResponseObject;
import com.betacom.backend.services.interfaces.products.ProductServices;

@RestController
@RequestMapping("/app/product")
@CrossOrigin(origins = "*")
public class ProductController {

    @Autowired
    Logger log;

    @Autowired
    ProductServices productServices;
    
    
    
    

    @PostMapping("/create")
    public ResponseBase create(@RequestBody(required = true) ProductRequest req){
        log.debug("PC : Product create request received:" + req);
        ResponseBase r = new ResponseBase();

        try{
            productServices.create(req);
            r.setRc(true);
            log.debug("PC: Product created");

        } catch (Exception e) {
            r.setRc(false);
            r.setMsg(e.getMessage());
            log.debug("PC: Error in product creation");
        }

        return r;
    }

    @GetMapping("/list")
    public ResponseList<ProductDTO> list(@RequestParam(defaultValue = "EN")  String lang){
        log.debug("PC: Product list request received");
        ResponseList<ProductDTO> r = new ResponseList<ProductDTO>();

        try{
            r.setDati(productServices.list(lang));
            r.setRc(true);
            log.debug("PC: Product list done");
        }catch (Exception e) {
            r.setRc(false);
            r.setMsg(e.getMessage());
            log.debug("PC: Error in product list");
        }

        return r;
    }
    
    @GetMapping("/filteredList")
    public ResponseList<ProductDTO> filteredList(
    		@RequestParam(required=false) List<String> types,
    		@RequestParam(required=false) Double minPrice,
    		@RequestParam(required=false) Double maxPrice,
    		@RequestParam(required=false) List<String> brands,
    		@RequestParam(defaultValue = "EN")  String lang){
        log.debug("PC: Product filteredList request received");
        ResponseList<ProductDTO> r = new ResponseList<ProductDTO>();

        try{
            r.setDati(productServices.filteredList(types, minPrice, maxPrice, brands, lang));
            r.setRc(true);
            log.debug("PC: Product filteredList done");
        }catch (Exception e) {
            r.setRc(false);
            r.setMsg(e.getMessage());
            log.debug("PC: Error in product filteredList");
        }

        return r;
    }

    @GetMapping("/get")
    public ResponseObject<ProductDTO> get(@RequestParam Long id ,@RequestParam(defaultValue = "EN") String lang){
        log.debug("PC: Product get request received for id:"+id);
        ResponseObject<ProductDTO> r = new  ResponseObject<ProductDTO>();

        try{
        	//da modificare
            r.setDati(productServices.get(id,lang));
            r.setRc(true);
            log.debug("PC: Product get done for id:"+id);
        }catch(Exception e){
            r.setRc(false);
            r.setMsg(e.getMessage());
            log.debug("PC: Error in product get for id:"+id);
        }

        return r;
    }

    @PostMapping("/update")
    public ResponseBase update(@RequestBody(required = true)ProductRequest req){
        log.debug("PC: Product update request received:" + req);
        ResponseBase r = new ResponseBase();

        try{
            productServices.update(req);
            r.setRc(true);
            log.debug("PC: Product updated");
        }catch(Exception e){
            r.setRc(false);
            r.setMsg(e.getMessage());
            log.debug("PC: Error in product update");
        }

        return r;
    }

    @PostMapping("/delete")
    public ResponseBase delete(@RequestBody ProductRequest req){
        log.debug("PC: Product delete request received for id:"+ req.getId());

        ResponseBase r = new  ResponseBase();

        try{
            productServices.delete(req.getId());
            r.setRc(true);
            log.debug("PC: Product deleted for id:"+ req.getId());
        }catch(Exception e){
            r.setRc(false);
            r.setMsg(e.getMessage());
            log.debug("PC: Error in product delete for id:"+ req.getId());
        }

        return r;
    }


}
