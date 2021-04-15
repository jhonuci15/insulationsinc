package insulations.inventory.msscproductservice.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.net.ssl.SSLSession;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;


@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {


    @Id
    @Type(type="org.hibernate.type.UUIDCharType")
    @Column(length = 36, columnDefinition = "varchar(36)", updatable = false, nullable = false)
    private Long id;

    @Column(unique = true)
    private String upc;

    @NotBlank
    private String name;
    private String type;
    private String uom;
    private BigDecimal price;
    private boolean onInventory;
    private Integer QuantityOnInventory;

}
