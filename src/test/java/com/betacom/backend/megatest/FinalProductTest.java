//package com.betacom.backend.megatest;
//
//import com.betacom.backend.controller.products.*;
//import com.betacom.backend.request.products.CpuRequest;
//import com.betacom.backend.request.products.GpuRequest;
//import com.betacom.backend.request.products.MotherboardRequest;
//import com.betacom.backend.request.products.ProductRequest;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.MethodOrderer;
//import org.junit.jupiter.api.Order;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestMethodOrder;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//@SpringBootTest
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//public class FinalProductTest {
//
//
//    @Autowired private CpuController cpuController;
//    @Autowired private GpuController gpuController;
//    @Autowired private MotherboardController motherboardController;
//    @Autowired private ProductController productController;
//    @Autowired private PsuController psuController;
//    @Autowired private RamController ramController;
//
//
//    @Test
//    @Order(1)
//    public void createProducts(){
//        Assertions.assertThat(productController.create(null)).isNotNull();
//        Assertions.assertThat(productController.create(null).getRc()).isEqualTo(false);
//
//        ProductRequest productRequest = new ProductRequest();
//        Assertions.assertThat(productController.create(productRequest)).isNotNull();
//        Assertions.assertThat(productController.create(productRequest).getRc()).isEqualTo(false);
//        /*
//            prodotti in generale
//         */
//
//        productRequest = new ProductRequest("tplink","wifimaster","scheda di rete tp link wifi di ultima generazione, permette comunicazione interplanetare in tempo reale, usa fibra stellre", 300,74.99);
//        Assertions.assertThat(productController.create(productRequest).getRc()).isEqualTo(true);
//
//        productRequest = new ProductRequest("tplink","bluetoothmaster","scheda di rete tp link bluetooth di ultima generazione, permette comunicazione interplanetare in tempo reale, usa fibra stellre", 99,34.99);
//        Assertions.assertThat(productController.create(productRequest).getRc()).isEqualTo(true);
//
//        productRequest = new ProductRequest("luce","colorata", "scheda di rete tp link bluetooth di ultima generazione, permette comunicazione interplanetare in tempo reale, usa fibra stellre", 99,34.99);
//        Assertions.assertThat(productController.create(productRequest).getRc()).isEqualTo(true);
//        productRequest = new ProductRequest("noctua","fan", "Ventola da 120mm", 150,14.99);
//        Assertions.assertThat(productController.create(productRequest).getRc()).isEqualTo(true);
//
//        productRequest = new ProductRequest("pasta","termica", "pasta da applicare tra processorte e dissipatore", 150,14.99);
//        Assertions.assertThat(productController.create(productRequest).getRc()).isEqualTo(true);
//
//        productRequest = new ProductRequest("dissi","patore", "dispositivo di raffredamento", 30,44.99);
//        Assertions.assertThat(productController.create(productRequest).getRc()).isEqualTo(true);
//
//
//        productRequest = new ProductRequest("update","me","elemento da aggiornare in futuro e poi cancellare",100,999999d);
//        Assertions.assertThat(productController.create(productRequest).getRc()).isEqualTo(true);
//        Assertions.assertThat(productController.create(productRequest).getRc()).isEqualTo(true);
//        Assertions.assertThat(productController.create(productRequest).getRc()).isEqualTo(true);
//
//        /*
//        creazione cpu
//         */
//
//        CpuRequest cpuRequest = new CpuRequest();
//        Assertions.assertThat(cpuController.create(null).getRc()).isEqualTo(false);
//        Assertions.assertThat(cpuController.create(cpuRequest).getRc()).isEqualTo(false);
//
//        cpuRequest = new CpuRequest("And","ryzen 5600","processore amd di generazione ryzen 5600",40,123.12,3.9,12);
//        Assertions.assertThat(cpuController.create(cpuRequest).getRc()).isEqualTo(true);
//
//        cpuRequest = new CpuRequest("Intel","I5 7600k","processore intel  di generazione i5 7600k",40,125.12,3.6,8);
//        Assertions.assertThat(cpuController.create(cpuRequest).getRc()).isEqualTo(true);
//
//        cpuRequest = new CpuRequest("And","ryzen 9600","processore amd di generazione ryzen 9600",40,150.9,3.2,12);
//        Assertions.assertThat(cpuController.create(cpuRequest).getRc()).isEqualTo(true);
//
//        cpuRequest = new CpuRequest("Intel","i7 8650","processore intel  di generazione i7 8650",40,300.12,4.2,8);
//        Assertions.assertThat(cpuController.create(cpuRequest).getRc()).isEqualTo(true);
//
//         /*
//        creazione gpu
//         */
//
//        Assertions.assertThat(gpuController.create(null).getRc()).isEqualTo(false);
//        GpuRequest gpuRequest = new GpuRequest();
//        Assertions.assertThat(gpuController.create(gpuRequest).getRc()).isEqualTo(false);
//
//        gpuRequest = new GpuRequest("nvidia","rtx 4050","graphic card made for gaming and work",80,299.90,7,3.2);
//        Assertions.assertThat(gpuController.create(gpuRequest).getRc()).isEqualTo(true);
//
//        gpuRequest = new GpuRequest("amd","rx6700xt","graphic card made for gaming and work",80,199.90,12,3.2);
//        Assertions.assertThat(gpuController.create(gpuRequest).getRc()).isEqualTo(true);
//
//        /*
//        motherboardController
//         */
//
//        Assertions.assertThat(motherboardController.create(null).getRc()).isEqualTo(false);
//        MotherboardRequest motherboardRequest = new MotherboardRequest();
//        Assertions.assertThat(motherboardController.create(motherboardRequest).getRc()).isEqualTo(false);
//        motherboardRequest = new MotherboardRequest("Asus","AM5 B450","scheda madre asus per processori amd",30,115.45,"AMD");
//        Assertions.assertThat(motherboardController.create(motherboardRequest).getRc()).isEqualTo(true);
//
//
//
//
//    }
//
//
//}
