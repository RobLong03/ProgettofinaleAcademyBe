package com.betacom.backend.controller.order;


import com.betacom.backend.dto.order.OrderItemDTO;
import com.betacom.backend.request.order.OrderItemRequest;
import com.betacom.backend.response.ResponseBase;
import com.betacom.backend.response.ResponseList;
import com.betacom.backend.response.ResponseObject;
import com.betacom.backend.services.interfaces.order.OrderItemsServices;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/app/admin/order/items")
@CrossOrigin(origins = "*")
public class OrderItemController {


    @Autowired
    Logger log;

    @Autowired
    OrderItemsServices orderItemServices;

    @GetMapping("/listByOrder")
    public ResponseList<OrderItemDTO> listByOrder(@RequestParam Long id){

        ResponseList<OrderItemDTO> r = new ResponseList<OrderItemDTO>();

        try{
            r.setDati(orderItemServices.listByOrder(id));
            r.setRc(true);
        }catch (Exception e) {
            r.setRc(false);
            r.setMsg(e.getMessage());
        }

        return r;
    }

    @GetMapping("/get")
    public ResponseObject<OrderItemDTO> get(@RequestParam Long id){

        ResponseObject<OrderItemDTO> r = new ResponseObject<OrderItemDTO>();

        try{
            r.setDati(orderItemServices.get(id));
            r.setRc(true);
        }catch (Exception e) {
            r.setRc(false);
            r.setMsg(e.getMessage());
        }

        return r;
    }

    @PostMapping("/addItemToOrder")
    public ResponseBase addItemToOrder(@RequestBody(required = true) OrderItemRequest req){
        ResponseBase r = new ResponseBase();

        try{
            orderItemServices.addItemToOrder(req);
            r.setRc(true);

        } catch (Exception e) {
            r.setRc(false);
            r.setMsg(e.getMessage());
        }

        return r;
    }

    @PostMapping("/removeItemFromOrder")
    public ResponseBase removeItemFromOrder(@RequestBody(required = true) OrderItemRequest req){
        ResponseBase r = new ResponseBase();

        try{
            orderItemServices.removeItemFromOrder(req);
            r.setRc(true);

        } catch (Exception e) {
            r.setRc(false);
            r.setMsg(e.getMessage());
        }

        return r;
    }

    @PostMapping("/delete")
    public ResponseBase delete(@RequestBody OrderItemRequest req ){
        ResponseBase r = new  ResponseBase();

        try{
            orderItemServices.delete(req.getId());
            r.setRc(true);
        }catch(Exception e){
            r.setRc(false);
            r.setMsg(e.getMessage());
        }

        return r;
    }




}
