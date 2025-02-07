package com.betacom.backend.services.implementations.products;

import com.betacom.backend.dto.products.CpuDTO;
import com.betacom.backend.model.products.Cpu;
import com.betacom.backend.repositories.products.ICpuRepository;
import com.betacom.backend.request.products.CpuRequest;
import com.betacom.backend.services.interfaces.messages.MessageServices;
import com.betacom.backend.services.interfaces.products.CpuServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class CpuImpl implements CpuServices {

    @Autowired
    MessageServices msgS;

    @Autowired
    ICpuRepository cpuRep;

    @Override
    public List<CpuDTO> list() {
        List<Cpu> lCpu = cpuRep.findAll();

        return lCpu.stream().map(p ->
                new CpuDTO(p)
        ).collect(Collectors.toList());
    }

    @Override
    public CpuDTO get(Long id) throws Exception {
        if(id == null){
            throw new Exception(msgS.getMessage("missing-id-get"));
        }

        Optional<Cpu> cpu = cpuRep.findById(id);

        if(cpu.isPresent()){
            return new CpuDTO(cpu.get());
        }else{
            throw new Exception(msgS.getMessage("does-not-exist-get"));
        }
    }

    @Override
    public void create(CpuRequest req) throws Exception {
        if(mancanoAttributi(req))
            throw new Exception(msgS.getMessage("missing-attributes-create"));

        Cpu p = new Cpu(req );
        cpuRep.save(p);

    }

    private boolean mancanoAttributi(CpuRequest req) {
        return req.getDescription() == null || req.getDescription().isBlank()
                || req.getBrand() == null || req.getBrand().isBlank()
                || req.getModel() == null || req.getModel().isBlank()
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
