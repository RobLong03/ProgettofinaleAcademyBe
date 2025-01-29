package com.betacom.backend.services.implementations;

import com.betacom.backend.dto.ProdottoDTO;
import com.betacom.backend.model.Prodotto;
import com.betacom.backend.repositories.IProdottoRepositories;
import com.betacom.backend.request.ProdottoRequest;
import com.betacom.backend.services.interfaces.ProdottoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ProdottoImpl implements ProdottoServices {

    @Autowired
    IProdottoRepositories prodRep;

    @Override
    public List<ProdottoDTO> list() {
        List<Prodotto> lProd = prodRep.findAll();

            return lProd.stream().map(p ->
                    new ProdottoDTO(p)
            ).collect(Collectors.toList());
    }

    @Override
    public ProdottoDTO get(Long id) throws Exception{
        if(id == null){
            throw new Exception("missing-id");
        }

        Optional<Prodotto> prod = prodRep.findById(id);

        if(prod.isPresent()){
            return new ProdottoDTO(prod.get());
        }else{
            throw new Exception("not-found");
        }
    }

    @Override
    public void create(ProdottoRequest req) throws Exception {
        if(mancanoAttributi(req))
            throw new Exception("missing-attributes");

        Prodotto p = new Prodotto(req );
         prodRep.save(p);
    }



    @Override
    public void update(ProdottoRequest req) throws Exception {
        if(req.getId() == null){
            throw new Exception("missing-id");
        }

        if( prodRep.findById(req.getId()).isEmpty()){
            throw new Exception("does-not-exists");
        }

        Prodotto p = new Prodotto(req);

        prodRep.save(p);
    }

    @Override
    public void delete(Long id) throws Exception {
        if(id == null){
            throw new Exception("missing-id");
        }

        prodRep.deleteById(id);

    }

    private boolean mancanoAttributi(ProdottoRequest req) {
        return req.getDescrizione() == null || req.getDescrizione().isBlank()
                || req.getMarca() == null || req.getMarca().isBlank()
                || req.getModello() == null || req.getModello().isBlank()
                || req.getQuantita() == null;

    }
}
