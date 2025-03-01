package com.betacom.backend.services.interfaces.products;

import java.util.List;

import com.betacom.backend.dto.products.GpuDTO;
import com.betacom.backend.request.products.GpuRequest;

public interface GpuServices {

	List<GpuDTO> list(String lang);        //list per fare list di tutto

	GpuDTO get(Long id,String lang) throws Exception;         //get per prendere con id,

    //poi altri get o list con specifiche diverse si decide dopo

    String create(GpuRequest req ) throws Exception;

    void update(GpuRequest req) throws Exception;
}
