package insulations.inventory.msscproductservice.web.controller;

import insulations.inventory.msscproductservice.domain.Product;
import insulations.inventory.msscproductservice.services.ProductService;
import insulations.inventory.msscproductservice.services.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping(path = "/api/v1/product")
@RestController
public class ProductController {

    private final ProductServiceImpl productService;

    @Autowired
    public ProductController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllproducts(){

        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok().body(products);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity getProductById(@PathVariable("id") UUID id){

        return new ResponseEntity<>(productService.getProductById(id) , HttpStatus.OK);
    }

    @GetMapping("/product/{upc}")
    public ResponseEntity getProductByUpc(@PathVariable("upc") String upc){
        return new ResponseEntity<>(productService.getProductByUpc(upc), HttpStatus.OK);
    }

    @PostMapping(path = "/addProduct")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity addNewProduct(@RequestBody @Validated Product product) throws Exception{

        Optional<Product> saveProd = productService.getProductById(product.getId());
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(saveProd.get().getId()).toUri();
        return  ResponseEntity.created(location).build();
    }

    @PutMapping("/updateProduct/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity updateProduct(@PathVariable(value = "id") UUID id, @RequestBody @Validated Product product){

        productService.updateProduct(id, product);
        return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity deleteProduct(@PathVariable UUID id){

        productService.deleteProductById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

}
