package dev.adnan.productservice.services;

import dev.adnan.productservice.DTO.GenericProductDTO;

public interface ProductService {
    GenericProductDTO getProductById(Long id);
    GenericProductDTO createProduct(GenericProductDTO product);
}
