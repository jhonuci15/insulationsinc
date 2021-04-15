package insulations.inventory.msscproductservice.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import insulations.inventory.msscproductservice.domain.Product;
import insulations.inventory.msscproductservice.domain.ProductTypeEnum;
import insulations.inventory.msscproductservice.repositories.ProductRepository;
import insulations.inventory.msscproductservice.services.ProductService;
import insulations.inventory.msscproductservice.services.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ProductControllerTest {

    @Mock
    private ProductServiceImpl productService;

    @InjectMocks
    ProductController productController;


    MockMvc mockMvc;
    Product validProd;
    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {

        validProd = Product.builder()
                .id(1L)
                .name("FOAMGLAS")
                .type(ProductTypeEnum.FOAMGLAS.toString())
                .price(new BigDecimal("1.99"))
                .onInventory(true)
                .upc("8798776")
                .build();

        /*mockMvc = MockMvcBuilders
                .standaloneSetup(productController)
                .build();*/

    }

    @Test
    void findAll_TodosFound_ShouldReturnFoundTodoEntries() throws Exception{


    }

    @Test
    void CreateNewProduct() throws JsonProcessingException, Exception{

        Product newProd = validProd;
        Mockito.when(productService.saveProduct(newProd)).thenReturn(newProd);
        String url = "products/save";
        mockMvc.perform(post(url).contentType("applications/json").content(objectMapper.writeValueAsString(newProd)))
                .andExpect(status().isOk())
        .andExpect(content().string("1L"));

    }

    @Test
    void ProductNameMustNotBeBlank() throws JsonProcessingException, Exception{

        Product newProd = new Product();

        String url = "products/save";
        mockMvc.perform(post(url).contentType("applications/json").content(objectMapper.writeValueAsString(newProd)))
                .andExpect(status().isBadRequest());
        Mockito.verify(productService, times(0)).saveProduct(newProd);
    }
}