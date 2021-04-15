package insulations.inventory.msscproductservice.repositories;

import insulations.inventory.msscproductservice.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findProductByName(String name);

    Product findProductsById(Long id);

    Optional<Product> findProductByUpc(String upc);
}
