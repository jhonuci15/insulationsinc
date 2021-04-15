package insulations.inventory.msscproductservice.services;

import insulations.inventory.msscproductservice.domain.Product;
import insulations.inventory.msscproductservice.domain.ProductTypeEnum;
import insulations.inventory.msscproductservice.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl underTest;

    Product validProd;

    @BeforeEach
    void setup(){

        validProd = Product.builder()
                .id(1L)
                .name("FOAMGLAS")
                .type(ProductTypeEnum.FOAMGLAS.toString())
                .price(new BigDecimal("1.99"))
                .onInventory(true)
                .upc("8798776")
                .build();

        underTest = new ProductServiceImpl(productRepository);

    }

    @Test
    void CanGetAllProducts() {

        underTest.getAllProducts();
        verify(productRepository).findAll();

    }

    @Test
    void getProductById() {

        Product product = validProd;
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        Product foundProd = underTest.getProductById(1L);
        assertThat(foundProd).isNotNull();
        verify(productRepository).findById(1L);
    }

    @Test
    void getProductByUpc() {

        Product product = validProd;
        when(productRepository.findProductByUpc(product.getUpc())).thenReturn(Optional.of(product));
        Product foundProd = underTest.getProductByUpc(product.getUpc());
        assertThat(foundProd).isNotNull();
        verify(productRepository).findProductByUpc(product.getUpc());
    }

    @Test
    void getProductByName() {

    }

    @Test
    void canAddNewProduct() {

        Product newProd = validProd;
        underTest.addNewProduct(newProd);
        ArgumentCaptor<Product> productArgumentCaptor = ArgumentCaptor.forClass(Product.class);
        verify(productRepository).save(productArgumentCaptor.capture());
        Product captureProduct = productArgumentCaptor.getValue();
        assertThat(captureProduct).isEqualTo(newProd);

    }

    @Test
    void getAllProducts() {
    }

    @Test
    void deleteProductById() {
        underTest.deleteProductById(1L);
        verify(productRepository, atLeastOnce()).deleteById(1L);
    }

    @Test
    void updateProduct() {
    }

    @Test
    void saveProduct() {
    }
}