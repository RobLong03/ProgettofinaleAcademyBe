package com.betacom.backend.RobertoTest;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.betacom.backend.controller.customer.CustomerController;
import com.betacom.backend.dto.customer.CustomerDTO;
import com.betacom.backend.model.customer.Customer;
import com.betacom.backend.model.wishlist.Wishlist;
import com.betacom.backend.repositories.customer.ICustomerRepository;
import com.betacom.backend.repositories.wishlist.IWishlistRepository;
import com.betacom.backend.request.customer.CustomerRequest;
import com.betacom.backend.response.ResponseBase;
import com.betacom.backend.response.ResponseList;
import com.betacom.backend.services.implementations.customer.CustomerImpl;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CustomerControllerTest {

    @Autowired
    private CustomerController customerController;

    @Autowired
    private Logger log;
    @Autowired
    private CustomerImpl customerService;

    @Autowired
    private ICustomerRepository customerRepository;

    @Autowired
    private IWishlistRepository wishlistRepository;

    @Test
    @Order(1)
    public void createCustomerTest() throws Exception {
        CustomerRequest customerRequest = new CustomerRequest();
        customerRequest.setName("jave Doe");
        customerRequest.setSurname("Doe");
        customerRequest.setEmail("jade.doe@example.com");
        customerRequest.setTaxId("9876543210");
        customerRequest.setPassword("Passwas");

        // Test valid creation
        ResponseBase response = customerController.create(customerRequest);
        Assertions.assertThat(response.getRc()).isTrue();

        // Test creation with missing attributes (name)
        customerRequest.setName(null);
        response = customerController.create(customerRequest);
        Assertions.assertThat(response.getRc()).isFalse();
        Assertions.assertThat(response.getMsg()).isEqualTo("missing-attributes-create");
    }

    @Test
    @Order(2)
    public void create2CustomerTest() throws Exception {
        CustomerRequest customerRequest = new CustomerRequest();
        customerRequest.setName("java Rob");
        customerRequest.setSurname("Rob");
        customerRequest.setEmail("as.doe@example.com");
        customerRequest.setTaxId("9a210");
        customerRequest.setPassword("MKMA81");

        // Test valid creation
        ResponseBase response = customerController.create(customerRequest);
        Assertions.assertThat(response.getRc()).isTrue();
        Assertions.assertThat(customerController.get(2L).getRc()).isTrue();
    }
    @Test
    @Order(3)
    public void listCustomerTest() throws Exception {
        // Test retrieving the list of customers
        ResponseList<CustomerDTO> response = customerController.list();

        Assertions.assertThat(response.getRc()).isTrue();
        Assertions.assertThat(response.getDati()).isNotEmpty(); // Ensure customers exist in the list
        response.getDati().forEach(z->log.debug("CUSTOMER   :"+z));
        
    }

    @Test
    @Order(4)
    public void updateCustomerTest() throws Exception {
        CustomerRequest customerRequest = new CustomerRequest();
        customerRequest.setId(1L); // Ensure this ID exists in the test database
        customerRequest.setName("Jane Doe");
        customerRequest.setSurname("Doe");
        customerRequest.setEmail("Qanedoe@example.com");
        customerRequest.setTaxId("9876543210");
        customerRequest.setPassword("asasas");

        // Test valid update
        ResponseBase response = customerController.update(customerRequest);
        Assertions.assertThat(response.getRc()).isTrue();

        // Test update with missing attributes (address)
        customerRequest.setName(null);
        response = customerController.update(customerRequest);
        Assertions.assertThat(response.getRc()).isFalse();
    }

    @Test
    @Order(5)
    public void deleteCustomerTest() throws Exception {
        Long customerId = 0L; // Ensure this ID exists in the test database

        // Test valid deletion
        ResponseBase response = customerController.delete(customerId);
        Assertions.assertThat(response.getRc()).isTrue();

        // Test deletion with invalid ID
        response = customerController.delete(null); // Non-existent ID
        Assertions.assertThat(response.getRc()).isFalse();
        Assertions.assertThat(response.getMsg()).isEqualTo("missing-id-delete");
    }


    @Test
    @Order(6)
    public void createValidCustomerTest() throws Exception {
        CustomerRequest request = new CustomerRequest();
        request.setName("Mario");
        request.setSurname("Rossi");
        request.setEmail("mario.rossi@example.com");
        request.setTaxId("MRORSS80A01H501Z");
        request.setPassword("SecurePassword");

        customerService.create(request);

        List<Customer> customers = customerRepository.findAll();
        Assertions.assertThat(customers).isNotEmpty();
        Assertions.assertThat(customers.get(2).getEmail()).isEqualTo("mario.rossi@example.com");

        List<Wishlist> wishlists = wishlistRepository.findAll();
        Assertions.assertThat(wishlists).isNotEmpty();
        Assertions.assertThat(wishlists.get(2).getCustomer().getEmail()).isEqualTo("mario.rossi@example.com");
    }

    @Test
    @Order(7)
    public void createCustomerWithDuplicateEmailTest() {
        CustomerRequest request = new CustomerRequest();
        request.setName("Luigi");
        request.setSurname("Verdi");
        request.setEmail("mario.rossi@example.com"); // Email già esistente
        request.setTaxId("LVRR80B01C501Z");
        request.setPassword("AnotherSecurePassword");

        Assertions.assertThatThrownBy(() -> customerService.create(request))
                .isInstanceOf(Exception.class)
                .hasMessage("email-already-in-use");
    }

    @Test
    @Order(8)
    public void getValidCustomerByIdTest() throws Exception {
        CustomerDTO customerDTO = customerService.get(2L); // Assicurati che questo ID esista
        Assertions.assertThat(customerDTO).isNotNull();
        Assertions.assertThat(customerDTO.getEmail()).isEqualTo("as.doe@example.com");
    }

    @Test
    @Order(9)
    public void getCustomerWithInvalidIdTest() {
        Assertions.assertThatThrownBy(() -> customerService.get(-1L))
                .isInstanceOf(Exception.class)
                .hasMessage("does-not-exist-get");
    }

    

    @Test
    @Order(10)
    public void updateCustomerWithMissingIdTest() {
        CustomerRequest request = new CustomerRequest();
        request.setName("No ID Name");
        request.setSurname("No ID Surname");
        request.setEmail("noid@example.com");
        request.setTaxId("TAXID001");
        request.setPassword("NoIDPassword");

        Assertions.assertThatThrownBy(() -> customerService.update(request))
                .isInstanceOf(Exception.class)
                .hasMessage("missing-id-update");
    }


    @Test
    @Order(11)
    public void deleteCustomerWithNullIdTest() {
        Assertions.assertThatThrownBy(() -> customerService.delete(null))
                .isInstanceOf(Exception.class);
    }

}





