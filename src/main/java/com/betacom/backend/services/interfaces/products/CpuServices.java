package com.betacom.backend.services.interfaces.products;

import com.betacom.backend.dto.products.CpuDTO;
import com.betacom.backend.request.products.CpuRequest;

import java.util.List;

public interface CpuServices {
    List<CpuDTO> list(String lang);        //list per fare list di tutto ?(optional)lang

    CpuDTO get(Long id,String lang) throws Exception;         //get per prendere con id,lang

    //poi altri get o list con specifiche diverse si decide dopo

    void create(CpuRequest req ) throws Exception;

    void update(CpuRequest req) throws Exception;


}
