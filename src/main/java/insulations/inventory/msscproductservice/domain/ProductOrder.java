package insulations.inventory.msscproductservice.domain;


import lombok.*;
import net.bytebuddy.implementation.bind.annotation.Super;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductOrder{

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    private LocalDate createdDate;
    private OrderStatusEnum orderStatus = OrderStatusEnum.NEW;
    private String orderNumber;

    @ManyToOne
    private Supervisor supervisor;

    @OneToMany(mappedBy = "productOrder", cascade = CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    private Set<ProductOrderElement> elements;

}
