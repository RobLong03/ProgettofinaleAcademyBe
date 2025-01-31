package com.betacom.backend.services.interfaces;

import com.betacom.backend.dto.CpuDTO;
import com.betacom.backend.request.CpuRequest;

import java.util.List;

public interface CpuServices {
    List<CpuDTO> list();        //list per fare list di tutto

    CpuDTO get(Long id) throws Exception;         //get per prendere con id,

    //poi altri get o list con specifiche diverse si decide dopo

    void create(CpuRequest req ) throws Exception;

    void update(CpuRequest req) throws Exception;


}
