package insulations.inventory.msscproductservice.services;
import insulations.inventory.msscproductservice.domain.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductService {

    Optional<Product> getProductById(UUID id);
    void addNewProduct(Product product);


    }
