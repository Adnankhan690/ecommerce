package dev.adnan.productservice.controllers;

import dev.adnan.productservice.thirdPartyClients.productservice.FakeStoreProductDTO;
import dev.adnan.productservice.DTO.GenericProductDTO;
import dev.adnan.productservice.exceptions.NotFoundException;
import dev.adnan.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    ProductService productService;
    public ProductController(@Qualifier(value = "selfProductServiceImpl") ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public GenericProductDTO getProductById(@PathVariable("id") Long id) throws NotFoundException {
        return  productService.getProductById(id);
    }

    @GetMapping
    public List<GenericProductDTO> getAllProducts() {
        return productService.getAllProducts();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable("id") Long id) throws NotFoundException {
        productService.deleteById(id);
        return new ResponseEntity<>("Product with id " +id+ " deleted Successfully!", HttpStatus.OK);
    }
    @PostMapping
    public GenericProductDTO createProduct(@RequestBody GenericProductDTO product ) {
        return productService.createProduct(product);
    }

    @PutMapping("/{id}")
    public GenericProductDTO updateProductById(@RequestBody GenericProductDTO product, @PathVariable("id") Long id)
    throws NotFoundException {
        return productService.updateProductById(product, id);
    }

}
