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
    public Product getProductById(Long id) {

        return productRepository.findProductsById(id);
    }

    public Product getProductByUpc(String upc){

        return productRepository.findProductByUpc(upc).orElseThrow(NotFoundException::new);
    }


    public Product getProductByName(String name){

        if(productRepository.findProductByName(name) == null ){

            throw new IllegalStateException();
        }

        return productRepository.findProductByName(name);
    }

    @Override
    public void addNewProduct(Product product) {

        if(productRepository.findProductByName(product.getName())== null){

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

    public void updateProduct(Long id, Product product){

        if(productRepository.findById(id).isPresent()){

            Product prod = productRepository.findById(id).get();
            prod.setName(product.getName());
            prod.setPrice(product.getPrice());
            prod.setUpc(product.getUpc());
            productRepository.save(prod);
        }
        else{


        }
    }

    public Product saveProduct(Product updatedProduct) {

        return productRepository.save(updatedProduct);
    }

    public void deleteProductById(Long id) {

        boolean e = productRepository.existsById(id);
        if(!e){

            throw new IllegalStateException("Product with id " + id + " does not exists");
        }

        productRepository.deleteById(id);
    }
}
