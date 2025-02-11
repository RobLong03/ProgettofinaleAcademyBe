package com.betacom.backend.services.interfaces.administrator;


import com.betacom.backend.dto.SignInDTO;
import com.betacom.backend.dto.administrator.AdministratorDTO;
import com.betacom.backend.request.SignInRequest;
import com.betacom.backend.request.administrator.AdministratorRequest;

import java.util.List;

public interface AdministratorServices {

    List<AdministratorDTO> list();

    AdministratorDTO get(Long id) throws Exception;

    void create(AdministratorRequest req) throws Exception;

    void update(AdministratorRequest req) throws Exception;

    void delete(Long id) throws Exception;

    SignInDTO signIn(SignInRequest req);
}
