package insulations.inventory.msscproductservice.web.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class ProductPageList extends PageImpl<ProductDto> {

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public ProductPageList(@JsonProperty("content") List<ProductDto> content,
                         @JsonProperty("number") int number,
                         @JsonProperty("size") int size,
                         @JsonProperty("totalElements") Long totalElements)     {

        super(content, PageRequest.of(number, size), totalElements);
    }

    public ProductPageList(List<ProductDto> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

    public ProductPageList(List<ProductDto> content) {
        super(content);
    }

}
