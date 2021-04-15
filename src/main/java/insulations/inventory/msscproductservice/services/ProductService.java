package insulations.inventory.msscproductservice.services;
import insulations.inventory.msscproductservice.domain.Product;

import java.util.Optional;
import java.util.UUID;

public interface ProductService {

    Product getProductById(Long id);
    void addNewProduct(Product product);

    }
