package com.betacom.backend.services.implementations.administrator;

import com.betacom.backend.dto.administrator.AdministratorDTO;
import com.betacom.backend.model.administrator.Administrator;
import com.betacom.backend.repositories.administrator.IAdministratorRepository;
import com.betacom.backend.request.administrator.AdministratorRequest;
import com.betacom.backend.services.interfaces.administrator.AdministratorServices;
import com.betacom.backend.services.interfaces.messages.MessageServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdministratorImpl implements AdministratorServices {

    @Autowired
    IAdministratorRepository admRep;

    @Autowired
    MessageServices msgS;

    @Override
    public List<AdministratorDTO> list() {
        List<Administrator> lAdm = admRep.findAll();

        return lAdm.stream().map(p ->
                new AdministratorDTO(p)
        ).collect(Collectors.toList());
    }

    @Override
    public AdministratorDTO get(Long id) throws Exception {
        if (id == null) {
            throw new Exception(msgS.getMessage("missing-id-get"));
        }

       Optional<Administrator> adm = admRep.findById(id);
       if(adm.isPresent()){
           return new AdministratorDTO(adm.get());
       }else{
           throw new Exception(msgS.getMessage("does-not-exist-get"));
       }
    }

    @Override
    public void create(AdministratorRequest req) throws Exception {
        if(missingattributes(req))
            throw new Exception(msgS.getMessage("missing-attributes-create"));

        Administrator adm = new Administrator(req);

    }

    private boolean missingattributes(AdministratorRequest req) {
        return req.getEmail() == null || req.getEmail().isBlank()
                || req.getPassword() == null || req.getPassword().isBlank()
                || req.getUsername() == null || req.getUsername().isBlank();
    }

    @Override
    public void update(AdministratorRequest req) throws Exception {


        if(req.getId() == null){
            throw new Exception(msgS.getMessage("missing-id-update"));
        }
        if(admRep.findById(req.getId()).isEmpty()){
            throw new Exception(msgS.getMessage("does-not-exist-update"));
        }
        Administrator adm = new Administrator(req);
        admRep.save(adm);

    }

    @Override
    public void delete(Long id) throws Exception {
        if(id==null){
            throw new Exception(msgS.getMessage("missing-id-update"));
        }
        admRep.deleteById(id);
    }
}
