package com.betacom.backend.controller.products;

import com.betacom.backend.dto.products.CpuDTO;
import com.betacom.backend.request.products.CpuRequest;
import com.betacom.backend.response.ResponseBase;
import com.betacom.backend.response.ResponseList;
import com.betacom.backend.response.ResponseObject;
import com.betacom.backend.services.interfaces.products.CpuServices;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/app/product/cpu")
public class CpuController {

    @Autowired
    Logger log;

    @Autowired
    CpuServices cpuServices;

    @PostMapping("/create")
    public ResponseBase create(@RequestBody(required = true)CpuRequest req){
        ResponseBase r = new ResponseBase();

        try{
            cpuServices.create(req);
            r.setRc(true);

        } catch (Exception e) {
            r.setRc(false);
            r.setMsg(e.getMessage());
        }

        return r;
    }

    @GetMapping("/list")
    public ResponseList<CpuDTO> list(){
        ResponseList<CpuDTO> r = new ResponseList<CpuDTO>();

        try{
            r.setDati(cpuServices.list());
            r.setRc(true);
        }catch (Exception e) {
            r.setRc(false);
            r.setMsg(e.getMessage());
        }

        return r;
    }

    @GetMapping("/get")
    public ResponseObject<CpuDTO> get(@RequestParam Long id ){
        ResponseObject<CpuDTO> r = new  ResponseObject<CpuDTO>();

        try{
            r.setDati(cpuServices.get(id));
        }catch(Exception e){
            r.setRc(false);
            r.setMsg(e.getMessage());
        }

        return r;
    }

    @PostMapping()
    public ResponseBase update(@RequestBody(required = true)CpuRequest req){
        ResponseBase r = new ResponseBase();

        try{
            cpuServices.update(req);
            r.setRc(true);
        }catch(Exception e){
            r.setRc(false);
            r.setMsg(e.getMessage());
        }

        return r;
    }

    //delete in prodotto

}
