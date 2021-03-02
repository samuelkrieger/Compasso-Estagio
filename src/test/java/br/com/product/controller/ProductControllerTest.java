package br.com.product.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.product.domain.Inventory;
import br.com.product.domain.Product;
import br.com.product.helpers.ProductCreatedResponseHelper;
import br.com.product.helpers.ProductErrorResponseHelper;
import br.com.product.repository.ProductRepository;
import br.com.product.response.ProductCreatedResponse;
import br.com.product.response.ProductErrorResponse;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductControllerTest {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private ProductRepository repository;
	
    @Test
    public void testProductCreation() throws Exception {
      
        Product product = new Product(

                BigDecimal.TEN,
                "Product Test",
                new Inventory(5)
        );
        String json = MAPPER.writeValueAsString(product);

     
        when(this.repository.save(any(Product.class))).thenReturn(product);
        MvcResult result = mockMvc.perform(
                post("/api/products")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        ).andReturn();

    
        verify(this.repository, times(1)).save(any(Product.class));
        assertNotNull(result);
        MockHttpServletResponse response = result.getResponse();
        assertNotNull(response);
        assertEquals(201, response.getStatus());
        ProductCreatedResponse responseBody = MAPPER.readValue(response.getContentAsString(), ProductCreatedResponseHelper.class);
        assertEquals("product-created", responseBody.getReason());
        assertEquals("", responseBody.getMessage());
        assertEquals(HttpStatus.CREATED, responseBody.getStatus());
        assertNotNull(responseBody.getResult());
    }

    @Test
    public void testProductCreationThrowsBadProductException() throws Exception {
   
        Product product = new Product(
                BigDecimal.TEN,
                "Product Test",
                new Inventory(5)
        );
        String json = MAPPER.writeValueAsString(product);

  
        when(this.repository.save(any(Product.class))).thenThrow(new RuntimeException("invalid-product-name"));
        MvcResult result = mockMvc.perform(
                post("/api/products")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        ).andReturn();

      
        verify(this.repository, times(1)).save(any(Product.class));
        assertNotNull(result);
        MockHttpServletResponse response = result.getResponse();
        assertNotNull(response);
        assertEquals(400, response.getStatus());
        ProductErrorResponse responseBody = MAPPER.readValue(response.getContentAsString(), ProductErrorResponseHelper.class);
        assertEquals("product-error", responseBody.getReason());
        assertEquals("invalid product for name=Product Test", responseBody.getMessage());
        assertEquals(HttpStatus.BAD_REQUEST, responseBody.getStatus());
        assertNull(responseBody.getResult());
    }

   
}