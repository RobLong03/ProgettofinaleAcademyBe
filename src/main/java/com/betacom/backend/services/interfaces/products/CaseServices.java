package com.betacom.backend.services.interfaces.products;

import java.util.List;

import com.betacom.backend.dto.products.CaseDTO;
import com.betacom.backend.request.products.CaseRequest;

public interface CaseServices {

	List<CaseDTO> list(String lang);        //list per fare list di tutto

    CaseDTO get(Long id,String lang) throws Exception;         //get per prendere con id,

    //poi altri get o list con specifiche diverse si decide dopo

    String create(CaseRequest req ) throws Exception;

    void update(CaseRequest req) throws Exception;

    void delete(Long id) throws Exception ;
}
