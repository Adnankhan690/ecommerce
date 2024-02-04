package dev.adnan.productservice.controllers;

import dev.adnan.productservice.DTO.GenericProductDTO;
import dev.adnan.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products/")
public class ProductController {

    ProductService productService;
    public ProductController(@Qualifier("fakeStoreProductService") ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("{id}")
    public GenericProductDTO getProductById(@PathVariable("id") Long id) {
        return  productService.getProductById(id);
    }
    @GetMapping("products")
    public void getAllProducts() {

    }
    //TODO: Implement by yourself Homework
    @DeleteMapping("{id}")
    public void deleteProductById() {

    }
    @PostMapping
    public GenericProductDTO createProduct(@RequestBody GenericProductDTO product ) {
        return productService.createProduct(product);
    }
    //TODO: Implement by yourself Homework
    @PutMapping("{id}")
    public void updateProductById() {

    }

}
