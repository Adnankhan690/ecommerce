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
    @DeleteMapping("{id}")
    public void deleteProductById() {

    }
    @PostMapping("{id}")
    public void createProduct() {

    }
    @PutMapping("{id}")
    public void updateProductById() {

    }

}
