package com.betacom.backend.controller.administrator;


import com.betacom.backend.dto.SignInDTO;
import com.betacom.backend.dto.administrator.AdministratorDTO;
import com.betacom.backend.request.SignInRequest;
import com.betacom.backend.request.administrator.AdministratorRequest;
import com.betacom.backend.response.ResponseBase;
import com.betacom.backend.response.ResponseList;
import com.betacom.backend.response.ResponseObject;
import com.betacom.backend.services.interfaces.administrator.AdministratorServices;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/app/administrator/")
public class AdministratorController {

    @Autowired
    Logger log;

    @Autowired
    AdministratorServices administratorServices;

    @PostMapping("/create")
    public ResponseBase create(@RequestBody(required = true) AdministratorRequest req){
        log.debug("AC : Administrator create request received:" + req);
        ResponseBase r = new ResponseBase();

        try{
            administratorServices.create(req);
            r.setRc(true);
            log.debug("AC: Administrator created");
        } catch (Exception e) {
            r.setRc(false);
            r.setMsg(e.getMessage());
            log.debug("AC: Error in administrator creation");
        }

        return r;
    }

    @GetMapping("/list")
    public ResponseList<AdministratorDTO> list(){
        log.debug("AC: Administrator list request received");
        ResponseList<AdministratorDTO> r = new ResponseList<AdministratorDTO>();

        try{
            r.setDati(administratorServices.list());
            r.setRc(true);
            log.debug("AC: Administrator list done");
        }catch (Exception e) {
            r.setRc(false);
            r.setMsg(e.getMessage());
            log.debug("AC: Error in administrator list");
        }

        return r;
    }

    @GetMapping("/get")
    public ResponseObject<AdministratorDTO> get(@RequestParam Long id ){
        log.debug("AC: Administrator get request received for id:"+id);
        ResponseObject<AdministratorDTO> r = new  ResponseObject<AdministratorDTO>();

        try{
            r.setRc(true);
            r.setDati(administratorServices.get(id));
            log.debug("AC: Administrator get done for id:"+id);
        }catch(Exception e){
            r.setRc(false);
            r.setMsg(e.getMessage());
            log.debug("AC: Error in administrator get for id:"+id);
        }

        return r;
    }

    @PostMapping("/update")
    public ResponseBase update(@RequestBody(required = true)AdministratorRequest req){
        log.debug("AC: Administrator update request received:" + req);
        ResponseBase r = new ResponseBase();

        try{
            administratorServices.update(req);
            r.setRc(true);
            log.debug("AC: Administrator updated");
        }catch(Exception e){
            r.setRc(false);
            r.setMsg(e.getMessage());
            log.debug("AC: Error in administrator update");
        }

        return r;
    }

    @PostMapping("/delete")
    public ResponseBase delete(@RequestBody AdministratorRequest req ){
        log.debug("AC: Administrator delete request received for id:"+ req.getId());
        ResponseBase r = new  ResponseBase();

        try{
            administratorServices.delete(req.getId());
            r.setRc(true);
            log.debug("AC: Administrator deleted for id:"+ req.getId());
        }catch(Exception e){
            r.setRc(false);
            r.setMsg(e.getMessage());
            log.debug("AC: Error in administrator delete for id:"+ req.getId());
        }

        return r;
    }

    @PostMapping("/signIn")
    public SignInDTO signIn(@RequestBody SignInRequest req){
        log.debug("SignIn Request");
        return administratorServices.signIn(req);
    }


}
