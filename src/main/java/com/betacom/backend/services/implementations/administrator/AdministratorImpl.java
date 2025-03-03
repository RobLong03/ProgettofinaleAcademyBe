package com.betacom.backend.services.implementations.administrator;

import com.betacom.backend.dto.SignInDTO;
import com.betacom.backend.dto.administrator.AdministratorDTO;
import com.betacom.backend.model.administrator.Administrator;
import com.betacom.backend.repositories.administrator.IAdministratorRepository;
import com.betacom.backend.request.SignInRequest;
import com.betacom.backend.request.administrator.AdministratorRequest;
import com.betacom.backend.services.PasswordService;
import com.betacom.backend.services.interfaces.administrator.AdministratorServices;
import com.betacom.backend.services.interfaces.messages.MessageServices;
import com.betacom.backend.utils.Roles;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
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

    @Autowired
    Logger log;

    @Autowired
    private PasswordService passwordService;

    /**
     * Recupera la lista di tutti gli amministratori.
     * @return Una lista di AdministratorDTO.
     */
    @Override
    public List<AdministratorDTO> list() {
        log.debug("AI: list");
        List<Administrator> lAdm = admRep.findAll();

        return lAdm.stream().map(p ->
                new AdministratorDTO(p)
        ).collect(Collectors.toList());
    }

    /**
     * Recupera un amministratore per ID.
     * @param id L'ID dell'amministratore.
     * @return Un AdministratorDTO.
     * @throws Exception Se l'ID è mancante o l'amministratore non esiste.
     */
    @Override
    public AdministratorDTO get(Long id) throws Exception {
        log.debug("AI: get request, checking id");
        if (id == null) {
            throw new Exception(msgS.getMessage("missing-id-get"));
        }

        log.debug("AI: get request, attempting fetch");
        Optional<Administrator> adm = admRep.findById(id);
        if(adm.isPresent()){
            log.debug("AI: get request, returning administrator");
            return new AdministratorDTO(adm.get());
        }else{
            log.debug("AI: get request, not found");
            throw new Exception(msgS.getMessage("does-not-exist-get"));
        }
    }

    /**
     * Crea un nuovo amministratore.
     * @param req La richiesta contenente i dettagli dell'amministratore.
     * @throws Exception Se mancano attributi o l'username/email è già in uso.
     */
    @Override
    public void create(AdministratorRequest req) throws Exception {
        log.debug("AI: create request");
        if(missingattributes(req))
            throw new Exception(msgS.getMessage("missing-attributes-create"));

        Optional<Administrator> admin = admRep.findByUsername(req.getUsername());
        if(admin.isPresent()){
            throw new Exception(msgS.getMessage("username-already-in-use"));
        }

        admin = admRep.findByEmail(req.getEmail());
        if(admin.isPresent()){
            throw new Exception(msgS.getMessage("email-already-in-use"));
        }

        Administrator adm = new Administrator(req);
        adm.setPassword(passwordService.hashPassword(req.getPassword()));
        admRep.save(adm);
    }

    /**
     * Controlla se mancano attributi nella richiesta.
     * @param req La richiesta dell'amministratore.
     * @return true se mancano attributi, altrimenti false.
     */
    private boolean missingattributes(AdministratorRequest req) {
        return req.getEmail() == null || req.getEmail().isBlank()
                || req.getPassword() == null || req.getPassword().isBlank()
                || req.getUsername() == null || req.getUsername().isBlank();
    }

    /**
     * Aggiorna i dettagli di un amministratore.
     * @param req La richiesta contenente i nuovi dettagli.
     * @throws Exception Se l'ID è mancante o l'amministratore non esiste.
     */
    @Override
    public void update(AdministratorRequest req) throws Exception {
        log.debug("AI: update request");
        log.debug("AI: checking attributes");
        if(req.getId() == null){
            throw new Exception(msgS.getMessage("missing-id-update"));
        }

        Optional<Administrator> admOpt = admRep.findById(req.getId());
        if(admOpt.isEmpty()){
            throw new Exception(msgS.getMessage("does-not-exist-update"));
        }
        Administrator adm = admOpt.get();

        log.debug("AI: updating ....");

        if(req.getUsername() != null && !req.getUsername().isBlank()){
            adm.setUsername(req.getUsername());
        }

        if(req.getEmail() != null && !req.getEmail().isBlank()){
            adm.setEmail(req.getEmail());
        }

        if(req.getPassword() != null && !req.getPassword().isBlank()){
            adm.setPassword(passwordService.hashPassword(req.getPassword()));
        }

        admRep.save(adm);
        log.debug("AI: updating complete");
    }

    /**
     * Elimina un amministratore per ID.
     * @param id L'ID dell'amministratore da eliminare.
     * @throws Exception Se l'ID è mancante.
     */
    @Override
    public void delete(Long id) throws Exception {
        log.debug("AI: delete request");
        if(id==null){
            throw new Exception(msgS.getMessage("missing-id-update"));
        }
        log.debug("AI: deleting ....");
        admRep.deleteById(id);
        log.debug("AI: deleting complete");
    }

    /**
     * Effettua il login di un amministratore.
     * @param req La richiesta di login contenente l'username e la password.
     * @return Un SignInDTO con lo stato del login.
     */
    @Override
    public SignInDTO signIn(SignInRequest req) {
        log.debug("AI: signIn request received");
        SignInDTO resp = new SignInDTO();

        log.debug("AI: processing signin request");
        if(req.getUsername() == null || req.getUsername().isBlank() || req.getPwd() == null || req.getPwd().isBlank()){
            resp.setLogged(false);
            return resp;
        }

        Optional<Administrator> amm = admRep.findByUsername(req.getUsername());
        resp.setLogged(false);
        if(amm.isPresent()){
            if(passwordService.checkPassword(req.getPwd(), amm.get().getPassword())){
                resp.setLogged(true);
                resp.setRole(Roles.valueOf("ADMIN").toString());
            }
        }
        log.debug("AI: signIn processed");
        return resp;
    }
}