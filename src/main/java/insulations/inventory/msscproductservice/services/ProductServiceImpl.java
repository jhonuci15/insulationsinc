package insulations.inventory.msscproductservice.services;

import insulations.inventory.msscproductservice.domain.Product;
import insulations.inventory.msscproductservice.repositories.ProductRepository;
import insulations.inventory.msscproductservice.web.controller.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Optional<Product> getProductById(UUID id) {

        return Optional.of(productRepository.findProductsById(id)).orElseThrow(NotFoundException::new);
    }

    public Product getProductByUpc(String upc){

        return productRepository.findProductByUpc(upc).orElseThrow(NotFoundException::new);
    }

    @Override
    public void addNewProduct(Product product) {

        Optional<Product> prod = productRepository.findById(product.getId());

        if(productRepository.findById(prod.get().getId()).isPresent()){

            throw new IllegalStateException();
        }

        productRepository.save(product);
    }

    public List<Product> getAllProducts() {

        List<Product> productList = new ArrayList<>();
        Iterable<Product> products = productRepository.findAll();
        products.forEach(productList::add);
        return productList;

    }

    public void updateProduct(UUID id, Product product){

        if(productRepository.findById(id).isPresent()){

            Product prod = productRepository.findById(id).get();
            prod.setName(product.getName());
            prod.setName(prod.getName());
            prod.setPrice(product.getPrice());
            prod.setUpc(prod.getUpc());
            productRepository.save(prod);
        }
        else{


        }
    }

    public Product saveProduct(Product updatedProduct) {

        return productRepository.save(updatedProduct);
    }

    public void deleteProductById(UUID id) {

        productRepository.deleteById(id);
    }
}
