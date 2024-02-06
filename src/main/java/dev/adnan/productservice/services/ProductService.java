package dev.adnan.productservice.services;

import dev.adnan.productservice.DTO.FakeStoreProductDTO;
import dev.adnan.productservice.DTO.GenericProductDTO;
import dev.adnan.productservice.exceptions.NotFoundException;

import java.util.List;

public interface ProductService {
    GenericProductDTO getProductById(Long id) throws NotFoundException;
    GenericProductDTO createProduct(GenericProductDTO product);
    GenericProductDTO updateProductById(FakeStoreProductDTO product,Long id);
    GenericProductDTO deleteById(Long id);
    List<GenericProductDTO> getAllProducts();
}
