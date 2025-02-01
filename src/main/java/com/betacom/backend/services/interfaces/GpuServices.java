package com.betacom.backend.services.interfaces;

import java.util.List;

import com.betacom.backend.dto.GpuDTO;
import com.betacom.backend.request.GpuRequest;

public interface GpuServices {

	List<GpuDTO> list();        //list per fare list di tutto

	GpuDTO get(Long id) throws Exception;         //get per prendere con id,

    //poi altri get o list con specifiche diverse si decide dopo

    void create(GpuRequest req ) throws Exception;

    void update(GpuRequest req) throws Exception;
}
