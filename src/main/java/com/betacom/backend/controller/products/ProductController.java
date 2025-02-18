package com.betacom.backend.controller.products;


import com.betacom.backend.dto.products.ProductDTO;
import com.betacom.backend.request.products.ProductRequest;
import com.betacom.backend.response.ResponseBase;
import com.betacom.backend.response.ResponseList;
import com.betacom.backend.response.ResponseObject;
import com.betacom.backend.services.interfaces.products.ProductServices;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/app/product")
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
    public ResponseList<ProductDTO> list(){
        log.debug("PC: Product list request received");
        ResponseList<ProductDTO> r = new ResponseList<ProductDTO>();

        try{
            r.setDati(productServices.list());
            r.setRc(true);
            log.debug("PC: Product list done");
        }catch (Exception e) {
            r.setRc(false);
            r.setMsg(e.getMessage());
            log.debug("PC: Error in product list");
        }

        return r;
    }

    @GetMapping("/get")
    public ResponseObject<ProductDTO> get(@RequestParam Long id ){
        log.debug("PC: Product get request received for id:"+id);
        ResponseObject<ProductDTO> r = new  ResponseObject<ProductDTO>();

        try{
            r.setDati(productServices.get(id));
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

    @GetMapping("/delete")
    public ResponseBase delete(@RequestParam Long id ){
        log.debug("PC: Product delete request received for id:"+id);
        ResponseBase r = new  ResponseBase();

        try{
            productServices.delete(id);
            r.setRc(true);
            log.debug("PC: Product deleted for id:"+id);
        }catch(Exception e){
            r.setRc(false);
            r.setMsg(e.getMessage());
            log.debug("PC: Error in product delete for id:"+id);
        }

        return r;
    }


}
