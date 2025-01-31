package com.betacom.backend.services.interfaces;

import java.util.List;

import com.betacom.backend.dto.StorageDTO;
import com.betacom.backend.request.StorageRequest;

public interface StorageServices {

	List<StorageDTO> list();        //list per fare list di tutto

	StorageDTO get(Long id) throws Exception;         //get per prendere con id,

    //poi altri get o list con specifiche diverse si decide dopo

    void create(StorageRequest req ) throws Exception;

    void update(StorageRequest req) throws Exception;

    void delete(Long id) throws Exception;
}
