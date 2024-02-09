package dev.adnan.productservice.services;

import dev.adnan.productservice.DTO.GenericProductDTO;
import dev.adnan.productservice.exceptions.NotFoundException;
import dev.adnan.productservice.repositories.ProductRepository;
import dev.adnan.productservice.thirdPartyClients.productservice.FakeStoreProductDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("selfProductServiceImpl")
public class SelfProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    public SelfProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public GenericProductDTO getProductById(Long id) throws NotFoundException {
        return null;
    }

    @Override
    public GenericProductDTO createProduct(GenericProductDTO product) {
        return null;
    }

    @Override
    public GenericProductDTO updateProductById(FakeStoreProductDTO product, Long id) {
        return null;
    }

    @Override
    public GenericProductDTO deleteById(Long id) {
        return null;
    }

    @Override
    public List<GenericProductDTO> getAllProducts() {
        return null;
    }
}
