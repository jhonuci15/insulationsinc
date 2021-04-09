package insulations.inventory.msscproductservice.web.mapper;


import insulations.inventory.msscproductservice.domain.Product;
import insulations.inventory.msscproductservice.web.model.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class ProductMapperDecorator implements ProductMapper{

    private ProductMapper prodMapper;

    @Autowired
    public void setProdMapper(ProductMapper prodMapper) {
        this.prodMapper = prodMapper;
    }

    @Override
    public ProductDto productToProductDto(Product product) {
        return prodMapper.productToProductDto(product);
    }

    @Override
    public Product productDtoToProduct(ProductDto productDto){

        return prodMapper.productDtoToProduct(productDto);
    }

}
