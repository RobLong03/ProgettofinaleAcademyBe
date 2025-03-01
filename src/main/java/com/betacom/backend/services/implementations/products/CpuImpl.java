package com.betacom.backend.services.implementations.products;

import com.betacom.backend.dto.products.CaseDTO;
import com.betacom.backend.dto.products.CpuDTO;
import com.betacom.backend.dto.products.ProductDTO;
import com.betacom.backend.dto.products.ProductDescriptionDTO;
import com.betacom.backend.model.products.Cases;
import com.betacom.backend.model.products.Cpu;
import com.betacom.backend.model.products.Product;
import com.betacom.backend.repositories.products.ICpuRepository;
import com.betacom.backend.request.products.CpuRequest;
import com.betacom.backend.services.interfaces.messages.MessageServices;
import com.betacom.backend.services.interfaces.products.CpuServices;
import com.betacom.backend.services.interfaces.products.ProductDescriptionServices;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class CpuImpl implements CpuServices {

	@Autowired
    Logger log;
    @Autowired
    MessageServices msgS;

    @Autowired
    ICpuRepository cpuRep;
    @Autowired
    ProductDescriptionServices pdescS;

    //cambiare implementation e fare i settaggi come in product
    @Override
    public List<CpuDTO> list(String lang) {
    
        List<Cpu> lCpu = cpuRep.findAll();

        return lCpu.stream()
                .map(p -> {
                	CpuDTO dto = new CpuDTO(p);
                   
                    try {
                        dto.setDescription(pdescS.getDescription(p.getId(), lang));
                    } catch (Exception e) {
                    	return dto;
                    }
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public CpuDTO get(Long id,String lang) throws Exception {
        if(id == null){
            throw new Exception(msgS.getMessage("missing-id-get"));
        }
        Optional<Cpu> prod = cpuRep.findById(id);

        if(prod.isPresent()){
        	CpuDTO product = new CpuDTO(prod.get());
        	 ProductDescriptionDTO description = pdescS.getDescription(product.getId(), lang);
        	if (description != null)  product.setDescription(description);
            return product;
        }else{
            log.error("missing product");
            throw new Exception(msgS.getMessage("does-not-exist-get"));
        }
    }

    @Override
    public String create(CpuRequest req) throws Exception {
        if(mancanoAttributi(req))
            throw new Exception(msgS.getMessage("missing-attributes-create"));

        Cpu p = new Cpu(req );
        cpuRep.save(p);

        return ""+cpuRep.findAll().getLast().getId();
    }

    private boolean mancanoAttributi(CpuRequest req) {
        return req.getBrand() == null || req.getBrand().isBlank()
                || req.getModel() == null || req.getModel().isBlank()
                || req.getPrice() == null
                || req.getStock() == null
                || req.getCore() == null
                || req.getGhz() == null;
    }

    @Override
    public void update(CpuRequest req) throws Exception {
        if(req.getId() == null){
            throw new Exception(msgS.getMessage("missing-id-update"));
        }

        if( cpuRep.findById(req.getId()).isEmpty()){
            throw new Exception(msgS.getMessage("does-not-exist-update"));
        }

        Cpu p = new Cpu(req);

        cpuRep.save(p);
    }

}
