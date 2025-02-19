package com.betacom.backend.controller.order;


import com.betacom.backend.dto.order.OrderDTO;
import com.betacom.backend.request.order.OrderRequest;
import com.betacom.backend.response.ResponseBase;
import com.betacom.backend.response.ResponseList;
import com.betacom.backend.response.ResponseObject;
import com.betacom.backend.services.interfaces.order.OrderServices;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/app/admin/order")
public class OrderController {

    @Autowired
    Logger log;

    @Autowired
    OrderServices orderServices;

    @PostMapping("/create")
    public ResponseBase create(@RequestBody(required = true) OrderRequest req){
        ResponseBase r = new ResponseBase();

        try{
            orderServices.create(req);
            r.setRc(true);

        } catch (Exception e) {
            r.setRc(false);
            r.setMsg(e.getMessage());
        }

        return r;
    }

    @GetMapping("/list")
    public ResponseList<OrderDTO> list(){
        ResponseList<OrderDTO> r = new ResponseList<OrderDTO>();

        try{
            r.setDati(orderServices.list());
            r.setRc(true);
        }catch (Exception e) {
            r.setRc(false);
            r.setMsg(e.getMessage());
        }

        return r;
    }

    @GetMapping("/listByCustomer")
    public ResponseList<OrderDTO> listByCustomer(@RequestParam Long id){

        ResponseList<OrderDTO> r = new ResponseList<OrderDTO>();

        try{
            r.setDati(orderServices.listByCustomer(id));
            r.setRc(true);
        }catch (Exception e) {
            r.setRc(false);
            r.setMsg(e.getMessage());
        }

        return r;
    }

    @GetMapping("/get")
    public ResponseObject<OrderDTO> get(@RequestParam Long id ){
        ResponseObject<OrderDTO> r = new  ResponseObject<OrderDTO>();

        try{
            r.setDati(orderServices.get(id));
        }catch(Exception e){
            r.setRc(false);
            r.setMsg(e.getMessage());
        }

        return r;
    }

    @PostMapping("/update")
    public ResponseBase update(@RequestBody(required = true)OrderRequest req){
        ResponseBase r = new ResponseBase();

        try{
            orderServices.update(req);
            r.setRc(true);
        }catch(Exception e){
            r.setRc(false);
            r.setMsg(e.getMessage());
        }

        return r;
    }

    @PostMapping("/delete")
    public ResponseBase delete(@RequestBody(required = true) OrderRequest req){
        ResponseBase r = new  ResponseBase();

        try{
            orderServices.delete(req.getId());
        }catch(Exception e){
            r.setRc(false);
            r.setMsg(e.getMessage());
        }

        return r;
    }

}
