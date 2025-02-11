package com.betacom.backend.daniel;


import com.betacom.backend.controller.administrator.AdministratorController;
import com.betacom.backend.dto.SignInDTO;
import com.betacom.backend.dto.administrator.AdministratorDTO;
import com.betacom.backend.request.SignInRequest;
import com.betacom.backend.request.administrator.AdministratorRequest;
import com.betacom.backend.utils.Roles;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AmministratoreTest {

    @Autowired
    AdministratorController adminC;

    @Test
    @Order(1)
    public void  listVuoti(){
        List<AdministratorDTO> adminList = adminC.list().getDati();
        Assertions.assertThat(adminList).isNotNull();
        Assertions.assertThat(adminList.size()).isEqualTo(0);

        Assertions.assertThat(adminC.get(null).getRc()).isEqualTo(false);
        Assertions.assertThat(adminC.get(1L).getRc()).isEqualTo(false);

        Assertions.assertThat(adminC.signIn(new SignInRequest("ciao","ciao")).getLogged()).isEqualTo(false);
    }

    @Test
    @Order(2)
    public void testCreate(){
        AdministratorRequest req = new AdministratorRequest();
        Assertions.assertThat(adminC.create(req).getRc()).isEqualTo(false);

        req.setEmail("");
        Assertions.assertThat(adminC.create(req).getRc()).isEqualTo(false);

        req.setEmail("email@email.com");
        Assertions.assertThat(adminC.create(req).getRc()).isEqualTo(false);

        req.setPassword("");
        Assertions.assertThat(adminC.create(req).getRc()).isEqualTo(false);

        req.setPassword("password");
        Assertions.assertThat(adminC.create(req).getRc()).isEqualTo(false);

        req.setUsername("");
        Assertions.assertThat(adminC.create(req).getRc()).isEqualTo(false);

        req.setUsername("username");
        Assertions.assertThat(adminC.create(req).getRc()).isEqualTo(true);

        req = new AdministratorRequest("admin","admin@admin.it","adminpassword");
        Assertions.assertThat(adminC.create(req).getRc()).isEqualTo(true);
    }

    @Test
    @Order(3)
    public void testList(){
        List<AdministratorDTO> adminList = adminC.list().getDati();
        Assertions.assertThat(adminList).isNotNull();
        Assertions.assertThat(adminList.size()).isEqualTo(2);

        Assertions.assertThat(adminC.get(1L).getRc()).isEqualTo(true);

        AdministratorDTO first = adminC.get(adminList.getFirst().getId()).getDati();
        Assertions.assertThat(first).isNotNull();

        Assertions.assertThat(first.getId()).isEqualTo(adminList.getFirst().getId());
        Assertions.assertThat(first.getEmail()).isEqualTo(adminList.getFirst().getEmail());
        Assertions.assertThat(first.getPassword()).isEqualTo(adminList.getFirst().getPassword());
        Assertions.assertThat(first.getUsername()).isEqualTo(adminList.getFirst().getUsername());

        Assertions.assertThat(first.getUsername()).isEqualTo("username");
        Assertions.assertThat(first.getEmail()).isEqualTo("email@email.com");
        Assertions.assertThat(first.getPassword()).isEqualTo("password");
    }

    @Test
    @Order(4)
    public void signInTest(){
        SignInRequest req = new SignInRequest("admin","adminpassword");
        SignInDTO resp = adminC.signIn(req);
        Assertions.assertThat(resp).isNotNull();
        Assertions.assertThat(resp.getLogged()).isEqualTo(true);
        Assertions.assertThat(resp.getRole()).isEqualTo("ADMIN");
    }


}
