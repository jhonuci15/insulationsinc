package insulations.inventory.msscproductservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import insulations.inventory.msscproductservice.domain.Product;
import insulations.inventory.msscproductservice.domain.ProductTypeEnum;
import insulations.inventory.msscproductservice.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import java.math.BigDecimal;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class ProductControllerIntegrationTest {

    @Mock
    private ProductService productService;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() throws Exception{

        MockitoAnnotations.initMocks(this);
        //mockMvc = MockM
    }

    Product getValidProduct(){
        return Product.builder()
                .name("FOAMGLAS BLOCK")
                .type(ProductTypeEnum.FOAMGLAS.toString())
                .price(new BigDecimal("2.99"))
                .upc("89879879879")
                .build();
    }

    @Test
    public void productByIdWhenDoesNotExist() throws Exception {
        when(productService.getProductById(any()))
                .thenThrow(new Exception(""));

        MockHttpServletResponse response = mockMvc.perform(
                get("/product/{id}")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // then
        verify(productService, times(1)).getProductById(any());
        verifyNoMoreInteractions(productService);
    }


    /*@Test
    public void productByUpcWhenDoesNotExist() throws Exception {

        // given
        given(productService.getProductByUpc(any()))
                .willThrow(new Exception());

        // when
        MockHttpServletResponse response = mockMvc.perform(
                get("/product/")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
        assertThat(response.getContentAsString()).isEmpty();
    }*/

    @Test
    void addingProductTest() throws Exception {

        Product product = getValidProduct();
        String beerDtoJson = objectMapper.writeValueAsString(product);

        //given(productService.addNewProduct(product)).willReturn()

        mockMvc.perform(post("/api/v1/product/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson))
                .andExpect(status().isCreated());
    }
}