package com.betacom.backend.controller.products;


import com.betacom.backend.dto.products.ProductDTO;
import com.betacom.backend.request.products.ProductRequest;
import com.betacom.backend.response.ResponseBase;
import com.betacom.backend.response.ResponseList;
import com.betacom.backend.response.ResponseObject;
import com.betacom.backend.services.interfaces.products.CpuServices;
import com.betacom.backend.services.interfaces.products.ProductServices;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/app/product/")
public class ProductController {

    @Autowired
    Logger log;

    @Autowired
    ProductServices productServices;
    @Autowired
    private CpuServices cpuServices;

    @PostMapping("/create")
    public ResponseBase create(@RequestBody(required = true) ProductRequest req){
        ResponseBase r = new ResponseBase();

        try{
            productServices.create(req);
            r.setRc(true);

        } catch (Exception e) {
            r.setRc(false);
            r.setMsg(e.getMessage());
        }

        return r;
    }

    @GetMapping("/list")
    public ResponseList<ProductDTO> list(){
        ResponseList<ProductDTO> r = new ResponseList<ProductDTO>();

        try{
            r.setDati(productServices.list());
            r.setRc(true);
        }catch (Exception e) {
            r.setRc(false);
            r.setMsg(e.getMessage());
        }

        return r;
    }

    @GetMapping("/get")
    public ResponseObject<ProductDTO> get(@RequestParam Long id ){
        ResponseObject<ProductDTO> r = new  ResponseObject<ProductDTO>();

        try{
            r.setDati(productServices.get(id));
        }catch(Exception e){
            r.setRc(false);
            r.setMsg(e.getMessage());
        }

        return r;
    }

    @PostMapping()
    public ResponseBase update(@RequestBody(required = true)ProductRequest req){
        ResponseBase r = new ResponseBase();

        try{
            productServices.update(req);
            r.setRc(true);
        }catch(Exception e){
            r.setRc(false);
            r.setMsg(e.getMessage());
        }

        return r;
    }

    @GetMapping("/delete")
    public ResponseBase delete(@RequestParam Long id ){
        ResponseBase r = new  ResponseBase();

        try{
            productServices.delete(id);
        }catch(Exception e){
            r.setRc(false);
            r.setMsg(e.getMessage());
        }

        return r;
    }


}
