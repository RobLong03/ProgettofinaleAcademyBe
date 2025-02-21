package com.betacom.backend.controller.products;

import com.betacom.backend.dto.products.ProductDescriptionDTO;
import com.betacom.backend.request.products.ProductDescriptionRequest;
import com.betacom.backend.response.ResponseBase;
import com.betacom.backend.response.ResponseObject;
import com.betacom.backend.services.interfaces.products.ProductDescriptionServices;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/app/product-description")
public class ProductDescriptionController {

    @Autowired
    Logger log;

    @Autowired
    private ProductDescriptionServices productDescriptionServices;

    // Create
    @PostMapping("/create")
    public ResponseBase create(@RequestBody ProductDescriptionRequest req) {
        log.debug("PDC: Product description create request received: " + req);
        ResponseBase r = new ResponseBase();

        try {
            productDescriptionServices.createDescription(req);
            r.setRc(true);
            log.debug("PDC: Product description created successfully");
        } catch (Exception e) {
            r.setRc(false);
            r.setMsg(e.getMessage());
            log.error("PDC: Error during product description creation", e);
        }

        return r;
    }

    // Get by ID
    @GetMapping("/get")
    public ResponseObject<ProductDescriptionDTO> get(@RequestParam Long id) {
        log.debug("PDC: Product description get request received for id: " + id);
        ResponseObject<ProductDescriptionDTO> r = new ResponseObject<>();

        try {
            ProductDescriptionDTO dto = productDescriptionServices.getDescription(id);
            r.setDati(dto);
            r.setRc(true);
            log.debug("PDC: Product description retrieved for id: " + id);
        } catch (Exception e) {
            r.setRc(false);
            r.setMsg(e.getMessage());
            log.error("PDC: Error retrieving product description for id: " + id, e);
        }

        return r;
    }

    // Get by Product ID and Language
    @GetMapping("/get-by-product")
    public ResponseObject<ProductDescriptionDTO> getByProductAndLang(
            @RequestParam Long id_prodotto,
            @RequestParam(defaultValue = "EN") String lang) {

        log.debug("PDC: Product description get-by-product request received: id_prodotto=" + id_prodotto + ", lang=" + lang);
        ResponseObject<ProductDescriptionDTO> r = new ResponseObject<>();

        try {
            ProductDescriptionDTO dto = productDescriptionServices.getDescription(id_prodotto, lang);
            r.setDati(dto);
            r.setRc(true);
            log.debug("PDC: Product description retrieved for productId: " + id_prodotto + ", lang: " + lang);
        } catch (Exception e) {
            r.setRc(false);
            r.setMsg(e.getMessage());
            log.error("PDC: Error retrieving product description for productId: " + id_prodotto + ", lang: " + lang, e);
        }

        return r;
    }

    // Update
    @PostMapping("/update")
    public ResponseBase update(@RequestBody ProductDescriptionRequest req) {
        log.debug("PDC: Product description update request received: " + req);
        ResponseBase r = new ResponseBase();

        try {
            productDescriptionServices.updateDescription(req);
            r.setRc(true);
            log.debug("PDC: Product description updated successfully");
        } catch (Exception e) {
            r.setRc(false);
            r.setMsg(e.getMessage());
            log.error("PDC: Error updating product description", e);
        }

        return r;
    }

    // Delete
    @PostMapping("/delete")
    public ResponseBase delete(@RequestBody ProductDescriptionRequest req) {
        log.debug("PDC: Product description delete request received for id: " + req.getIdprodotto());
        ResponseBase r = new ResponseBase();

        try {
            ProductDescriptionRequest Dreq = new ProductDescriptionRequest();
            Dreq.setId(req.getId());
            productDescriptionServices.deleteDescription(req);
            r.setRc(true);
            log.debug("PDC: Product description deleted for id: " + req.getId());
        } catch (Exception e) {
            r.setRc(false);
            r.setMsg(e.getMessage());
            log.error("PDC: Error deleting product description for id: " + req.getId(), e);
        }

        return r;
    }
}
