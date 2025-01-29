package com.betacom.backend.services.interfaces;

import com.betacom.backend.dto.ProdottoDTO;
import com.betacom.backend.request.ProdottoRequest;

import java.util.List;

public interface ProdottoServices {
    List<ProdottoDTO> list();        //list per fare list di tutto

    ProdottoDTO get(Long id) throws Exception;         //get per prendere con id,

    //poi altri get o list con specifiche diverse si decide dopo

    void create(ProdottoRequest req ) throws Exception;

    void update(ProdottoRequest req) throws Exception;

    void delete(Long id) throws Exception ;

}