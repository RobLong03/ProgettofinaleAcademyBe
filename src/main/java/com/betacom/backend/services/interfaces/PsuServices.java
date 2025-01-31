package com.betacom.backend.services.interfaces;

import java.util.List;

import com.betacom.backend.dto.PsuDTO;
import com.betacom.backend.request.PsuRequest;

public interface PsuServices {

	List<PsuDTO> list();        //list per fare list di tutto

    PsuDTO get(Long id) throws Exception;         //get per prendere con id,

    //poi altri get o list con specifiche diverse si decide dopo

    void create(PsuRequest req ) throws Exception;

    void update(PsuRequest req) throws Exception;

    void delete(Long id) throws Exception ;
}
