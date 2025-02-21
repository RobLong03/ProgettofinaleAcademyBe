//package com.betacom.backend.RobertoTest;
//
//import java.util.List;
//
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.MethodOrderer;
//import org.junit.jupiter.api.Order;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestMethodOrder;
//import org.slf4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import com.betacom.backend.controller.customer.AddressController;
//import com.betacom.backend.dto.customer.AddressDTO;
//import com.betacom.backend.model.customer.Address;
//import com.betacom.backend.request.customer.AddressRequest;
//import com.betacom.backend.response.ResponseBase;
//import com.betacom.backend.response.ResponseList;
//import com.betacom.backend.response.ResponseObject;
//import com.betacom.backend.services.implementations.customer.AddressImpl;
//
//@SpringBootTest
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//public class AddressControllerTest {
//
//    @Autowired
//    private AddressController addressController;
//
//    @Autowired
//    private Logger log;
//    
//    @Autowired
//    private AddressImpl addressService;
//
//    @Test
//    @Order(1)
//    public void createAddressTest() throws Exception {
//        AddressRequest addressRequest = new AddressRequest();
//        addressRequest.setCustomerID(2L);
//        addressRequest.setCountry("Italy");
//        addressRequest.setCity("Torre");
//        addressRequest.setPostalCode("71017");
//        addressRequest.setStreet("Via Camillo de Nettirdis");
//        addressRequest.setHouseNumber(71);
//
//        // Test valid creation
//        ResponseBase response = addressController.create(addressRequest);
//        Assertions.assertThat(response.getRc()).isTrue();
//
//        // Test creation with missing customer ID
//        addressRequest.setCustomerID(null);
//        response = addressController.create(addressRequest);
//        Assertions.assertThat(response.getRc()).isFalse();
//        Assertions.assertThat(response.getMsg()).isNotBlank();
//    }
//
//    @Test
//    @Order(2)
//    public void getAddressTest() throws Exception {
//        // Test get with valid ID
//        ResponseObject<AddressDTO> response = addressController.get(1L);
//        Assertions.assertThat(response.getRc()).isTrue();
//        Assertions.assertThat(response.getDati()).isNotNull();
//        
//        // Test get with invalid ID
//        response = addressController.get(-1L);
//        Assertions.assertThat(response.getRc()).isFalse();
//        Assertions.assertThat(response.getMsg()).isNotBlank();
//    }
//
//    @Test
//    @Order(3)
//    public void listAddressesTest() throws Exception {
//        ResponseList<AddressDTO> response = addressController.list();
//        Assertions.assertThat(response.getRc()).isTrue();
//        Assertions.assertThat(response.getDati()).isNotEmpty();
//    }
//
//    @Test
//    @Order(4)
//    public void updateAddressTest() throws Exception {
//        AddressRequest addressRequest = new AddressRequest();
//
//        addressRequest.setId(1L);
//        addressRequest.setCustomerID(2L);
//        addressRequest.setCountry("Italy");
//        addressRequest.setCity("Updated City");
//        addressRequest.setPostalCode("71017");
//        addressRequest.setStreet("Updated Street");
//        addressRequest.setHouseNumber(100);
//
//        // Test update with valid data
//        ResponseBase response = addressController.update(addressRequest);
//        Assertions.assertThat(response.getRc()).isTrue();
//
//        // Test update with missing customer ID
//        addressRequest.setCustomerID(null);
//        response = addressController.update(addressRequest);
//        Assertions.assertThat(response.getRc()).isFalse();
//        Assertions.assertThat(response.getMsg()).isNotBlank();
//    }
//
//    @Test
//    @Order(5)
//    public void deleteAddressTest() throws Exception {
//        // Test delete with valid ID
//        ResponseBase response = addressController.delete(2L);
//        Assertions.assertThat(response.getRc()).isTrue();
//
//        // Test delete with invalid ID
//        response = addressController.delete(null);
//        Assertions.assertThat(response.getRc()).isFalse();
//        Assertions.assertThat(response.getMsg()).isNotBlank();
//    }
//    
//    @Test
//    @Order(6)
//    public void createValidAddressTest() throws Exception {
//        AddressRequest request = new AddressRequest();
//        request.setCustomerID(2L); // Assicurati che questo ID cliente esista
//        request.setCountry("Italy");
//        request.setCity("Torre");
//        request.setPostalCode("71017");
//        request.setStreet("Via Example");
//        request.setHouseNumber(10);
//
//        addressService.create(request);
//
//        List<AddressDTO> addresses = addressService.list();
//        Assertions.assertThat(addresses).isNotEmpty();
//        Assertions.assertThat(addresses.get(0).getCountry()).isEqualTo("Italy");
//    }
//
//    @Test
//    @Order(7)
//    public void createAddressMissingCustomerTest() {
//        AddressRequest request = new AddressRequest();
//        request.setCustomerID(null); // Customer ID mancante
//        request.setCountry("Italy");
//        request.setCity("Rome");
//        request.setPostalCode("71017");
//        request.setStreet("Via Test");
//        request.setHouseNumber(12);
//
//        Assertions.assertThatThrownBy(() -> addressService.create(request))
//                .isInstanceOf(Exception.class)
//                .hasMessage("missing-attributes-create");
//    }
//
//    @Test
//    @Order(8)
//    public void createAddressInvalidPostalCodeTest() {
//        AddressRequest request = new AddressRequest();
//        request.setCustomerID(2L); 
//        request.setCountry("Italy");
//        request.setCity("Rome");
//        request.setPostalCode(null); // Codice postale non valido
//        request.setStreet("Via Test");
//        request.setHouseNumber(12);
//
//        Assertions.assertThatThrownBy(() -> addressService.create(request))
//                .isInstanceOf(Exception.class);
//    }
//    
//    
//    @Test
//    @Order(9)
//    public void getAddressValidIdTest() throws Exception {
//        AddressDTO addressDTO = addressService.get(1L); // Assicurati che l'ID esista
//        Assertions.assertThat(addressDTO).isNotNull();
//        Assertions.assertThat(addressDTO.getCity()).isNotBlank();
//    }
//
//    @Test
//    @Order(10)
//    public void getAddressInvalidIdTest() {
//        Assertions.assertThatThrownBy(() -> addressService.get(-1L))
//                .isInstanceOf(Exception.class)
//                .hasMessage("does-not-exist-get");
//    }
//
//}
