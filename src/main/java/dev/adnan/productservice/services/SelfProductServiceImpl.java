package dev.adnan.productservice.services;

import dev.adnan.productservice.DTO.GenericProductDTO;
import dev.adnan.productservice.models.Product;
import org.springframework.stereotype.Service;

@Service("selfProductServiceImpl")
public class SelfProductServiceImpl implements ProductService {
    @Override
    public GenericProductDTO getProductById(Long id) {
        return new GenericProductDTO();
    }
}