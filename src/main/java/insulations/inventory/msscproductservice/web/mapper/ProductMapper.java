package insulations.inventory.msscproductservice.web.mapper;


import insulations.inventory.msscproductservice.domain.Product;
import insulations.inventory.msscproductservice.web.model.ProductDto;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

@Mapper(uses = DateMapper.class)
@DecoratedWith(ProductMapperDecorator.class)
public interface ProductMapper {

    ProductDto productToProductDto(Product product);
    Product productDtoToProduct(ProductDto productDto);
    //ProductDto productToProductDtoOnInventory(Product product);
}
