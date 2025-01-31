package com.betacom.backend.services.interfaces;

import java.util.List;

import com.betacom.backend.dto.CaseDTO;
import com.betacom.backend.request.CaseRequest;

public interface CaseServices {

	List<CaseDTO> list();        //list per fare list di tutto

    CaseDTO get(Long id) throws Exception;         //get per prendere con id,

    //poi altri get o list con specifiche diverse si decide dopo

    void create(CaseRequest req ) throws Exception;

    void update(CaseRequest req) throws Exception;

    void delete(Long id) throws Exception ;
}
