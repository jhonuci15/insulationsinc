package insulations.inventory.msscproductservice.web.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import insulations.inventory.msscproductservice.domain.ProductTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto implements Serializable {

    static final long serialVersionUID = -5767474740065181210L;


    @Null
    private UUID id;

    @Column(unique = true)
    @Null
    private String upc;

    @NotNull
    private String productName;

    @NotNull
    private ProductTypeEnum productType;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Positive
    @NotNull
    private BigDecimal price;

    private Integer QuantityOnInventory;
    private boolean OnInventory;

}
