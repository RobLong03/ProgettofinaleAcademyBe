package com.betacom.backend.megatest;

import com.betacom.backend.controller.products.CpuController;
import com.betacom.backend.controller.products.GpuController;
import com.betacom.backend.controller.products.MotherboardController;
import com.betacom.backend.controller.products.ProductController;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FinalProductTest {

    private ProductController productC;
    private GpuController gpuC;
    private MotherboardController motherboardC;
    private CpuController cpuC;


}
