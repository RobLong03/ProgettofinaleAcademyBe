package com.betacom.backend.daniel;

import com.betacom.backend.controller.products.CpuController;
import com.betacom.backend.dto.products.CpuDTO;
import com.betacom.backend.dto.products.CpuDTO;
import com.betacom.backend.request.products.CpuRequest;
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
public class testCpu {

    @Autowired
    CpuController cpuC;

    Long firstCpuId;
    Long secondCpuId;

    @Order(1)
    @Test
    public void testListVuoti(){
        List<CpuDTO> cpuList = cpuC.list().getDati();
        Assertions.assertThat(cpuList).isNotNull();
        Assertions.assertThat(cpuList.size()).isEqualTo(0);

        Assertions.assertThat(cpuC.get(null).getRc()).isEqualTo(false);
        Assertions.assertThat(cpuC.get(1L).getRc()).isEqualTo(false);
    }

    @Order(2)
    @Test
    public void testCreateVuoti(){
        Assertions.assertThat(cpuC.create(null).getRc()).isEqualTo(false);
        CpuRequest creq = new CpuRequest();
        Assertions.assertThat(cpuC.create(creq).getRc()).isEqualTo(false);

        creq.setDescription("");
        Assertions.assertThat(cpuC.create(creq).getRc()).isEqualTo(false);

        creq.setDescription("a generic description");
        Assertions.assertThat(cpuC.create(creq).getRc()).isEqualTo(false);

        creq.setBrand("");
        Assertions.assertThat(cpuC.create(creq).getRc()).isEqualTo(false);

        creq.setBrand("a brand");
        Assertions.assertThat(cpuC.create(creq).getRc()).isEqualTo(false);

        creq.setModel("");
        Assertions.assertThat(cpuC.create(creq).getRc()).isEqualTo(false);

        creq.setModel("a model");
        Assertions.assertThat(cpuC.create(creq).getRc()).isEqualTo(false);

        creq.setPrice(0.0);
        Assertions.assertThat(cpuC.create(creq).getRc()).isEqualTo(false);

        creq.setPrice(1.0);
        creq.setStock(15);
        Assertions.assertThat(cpuC.create(creq).getRc()).isEqualTo(false);

        creq.setCore(12);
        Assertions.assertThat(cpuC.create(creq).getRc()).isEqualTo(false);

        creq.setGhz(3.5);
        Assertions.assertThat(cpuC.create(creq).getRc()).isEqualTo(true);

        creq = new CpuRequest("aa","bb","cc",14,2.2,2.9,8);
        Assertions.assertThat(cpuC.create(creq).getRc()).isEqualTo(true);

    }

    @Test
    @Order(3)
    public void testList(){
        List<CpuDTO> cpuList = cpuC.list().getDati();
        Assertions.assertThat(cpuList).isNotNull();
        Assertions.assertThat(cpuList.size()).isEqualTo(2);

        this.firstCpuId =  cpuList.getFirst().getId();
        this.secondCpuId =  firstCpuId+1;

        CpuDTO p1 = cpuC.get(firstCpuId).getDati();
        Assertions.assertThat(cpuC.get(firstCpuId).getRc()).isEqualTo(true);

        Assertions.assertThat(p1.getBrand()).isEqualTo("a brand");
        Assertions.assertThat(p1.getModel()).isEqualTo("a model");
        Assertions.assertThat(p1.getPrice()).isEqualTo(1.0);
        Assertions.assertThat(p1.getStock()).isEqualTo(15);
        Assertions.assertThat(p1.getDescription()).isEqualTo("a generic description");
        Assertions.assertThat(p1.getGhz()).isEqualTo(3.5);
        Assertions.assertThat(p1.getCore()).isEqualTo(12);

    }

    @Test
    @Order(4)
    public void testUpdate(){
        Assertions.assertThat(cpuC.update(null).getRc()).isEqualTo(false);
        CpuRequest creq = new CpuRequest("aaaa","bbbbb","ccccc",15,2.3,3.9,10);

        secondCpuId = cpuC.list().getDati().getLast().getId();

        Assertions.assertThat(cpuC.update(creq).getRc()).isEqualTo(false);

        creq.setId(15L);
        Assertions.assertThat(cpuC.update(creq).getRc()).isEqualTo(false);

        creq.setId( secondCpuId);
        Assertions.assertThat(cpuC.update(creq).getRc()).isEqualTo(true);

        CpuDTO p1 = cpuC.get( secondCpuId).getDati();
        Assertions.assertThat(p1.getBrand()).isEqualTo("aaaa");
        Assertions.assertThat(p1.getModel()).isEqualTo("bbbbb");
        Assertions.assertThat(p1.getPrice()).isEqualTo(2.3);
        Assertions.assertThat(p1.getStock()).isEqualTo(15);
        Assertions.assertThat(p1.getDescription()).isEqualTo("ccccc");
        Assertions.assertThat(p1.getGhz()).isEqualTo(3.9);
        Assertions.assertThat(p1.getCore()).isEqualTo(10);

    }

}
