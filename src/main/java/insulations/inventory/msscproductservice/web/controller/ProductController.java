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

import java.math.BigDecimal;
import java.net.URI;
import java.util.Arrays;
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
    public ResponseEntity getProductById(@PathVariable("id") Long id){

        return new ResponseEntity<>(productService.getProductById(id) , HttpStatus.OK);
    }

    @GetMapping("/product/{upc}")
    public ResponseEntity getProductByUpc(@PathVariable("upc") String upc){
        return new ResponseEntity<>(productService.getProductByUpc(upc), HttpStatus.OK);
    }

    @PostMapping(path = "/addProduct")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity addNewProduct(@RequestBody @Validated Product product) throws Exception{

        Product saveProd = productService.getProductById(product.getId());
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(saveProd.getId()).toUri();
        return  ResponseEntity.created(location).build();
    }

    @PutMapping("/updateProduct/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity updateProduct(@PathVariable(value = "id") Long id, @RequestBody @Validated Product product){

        Optional<Product> productOptional = Optional.of(productService.getProductById(id));

        if(!productOptional.isPresent()){

            return ResponseEntity.notFound().build();
        }
            else{

                product.setId(id);

            }

        productService.updateProduct(id, product);
        return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity deleteProduct(@PathVariable Long id){

        productService.deleteProductById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @GetMapping("/search/{name}")
    public ResponseEntity searchProductsByName(@PathVariable("name") String name){

        //Product prod = Product.builder().name("Iphone14").price(new BigDecimal(123.23)).upc("0012123").QuantityOnInventory(23).build();
        //List<Product> result = Arrays.asList(prod);
        return new ResponseEntity<>(productService.getProductByName(name), HttpStatus.OK);
    }

}
