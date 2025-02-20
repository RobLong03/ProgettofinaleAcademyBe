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
@CrossOrigin(origins = "*")
public class CpuController {

    @Autowired
    Logger log;

    @Autowired
    CpuServices cpuServices;

    @PostMapping("/create")
    public ResponseBase create(@RequestBody(required = true)CpuRequest req){
        log.debug("CC : Cpu create request received:" + req);
        ResponseBase r = new ResponseBase();

        try{
            cpuServices.create(req);
            r.setRc(true);
            log.debug("CC: Cpu created");
        } catch (Exception e) {
            r.setRc(false);
            r.setMsg(e.getMessage());
            log.debug("CC: Error in cpu creation");
        }

        return r;
    }

    @GetMapping("/list")
    public ResponseList<CpuDTO> list(){
        log.debug("CC: Cpu list request received");
        ResponseList<CpuDTO> r = new ResponseList<CpuDTO>();

        try{
            r.setDati(cpuServices.list());
            r.setRc(true);
            log.debug("CC: Cpu list done");
        }catch (Exception e) {
            r.setRc(false);
            r.setMsg(e.getMessage());
            log.debug("CC: Error in cpu list");
        }

        return r;
    }

    @GetMapping("/get")
    public ResponseObject<CpuDTO> get(@RequestParam Long id ){
        log.debug("CC: Cpu get request received for id:"+id);
        ResponseObject<CpuDTO> r = new  ResponseObject<CpuDTO>();

        try{
            r.setDati(cpuServices.get(id));
            r.setRc(true);
            log.debug("CC: Cpu get done for id:"+id);
        }catch(Exception e){
            r.setRc(false);
            r.setMsg(e.getMessage());
            log.debug("CC: Error in cpu get for id:"+id);
        }

        return r;
    }

    @PostMapping()
    public ResponseBase update(@RequestBody(required = true)CpuRequest req){
        log.debug("CC: Cpu update request received:" + req);
        ResponseBase r = new ResponseBase();

        try{
            cpuServices.update(req);
            r.setRc(true);
            log.debug("CC: Cpu updated");
        }catch(Exception e){
            r.setRc(false);
            r.setMsg(e.getMessage());
            log.debug("CC: Error in cpu update");
        }

        return r;
    }

    //delete in prodotto

}
