package dev.adnan.productservice.services;

import dev.adnan.productservice.DTO.GenericProductDTO;
import dev.adnan.productservice.models.Product;
import org.springframework.stereotype.Service;

public interface ProductService {
    GenericProductDTO getProductById(Long id);
}
