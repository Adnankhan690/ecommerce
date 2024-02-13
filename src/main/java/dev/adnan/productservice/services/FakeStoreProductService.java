package dev.adnan.productservice.services;

import dev.adnan.productservice.thirdPartyClients.productservice.FakeStoreProductDTO;
import dev.adnan.productservice.DTO.GenericProductDTO;
import dev.adnan.productservice.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import dev.adnan.productservice.thirdPartyClients.productservice.FakeStoreProductServiceClient;

import java.util.ArrayList;
import java.util.List;
//@Primary
@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService {
    private FakeStoreProductServiceClient fakeStoreProductServiceClient;
    @Autowired
    public FakeStoreProductService(FakeStoreProductServiceClient fakeStoreProductServiceClient) {
        this.fakeStoreProductServiceClient = fakeStoreProductServiceClient;
    }
    public GenericProductDTO convertFakeStoreProductIntoGenericProduct(FakeStoreProductDTO fakeStoreProductDTO) {
        GenericProductDTO genericProductDTO = new GenericProductDTO();

        genericProductDTO.setId(fakeStoreProductDTO.getId());
        genericProductDTO.setDescription(fakeStoreProductDTO.getDescription());
        genericProductDTO.setCategory(fakeStoreProductDTO.getCategory());
        genericProductDTO.setImage(fakeStoreProductDTO.getImage());
        genericProductDTO.setPrice(fakeStoreProductDTO.getPrice());
        genericProductDTO.setTitle(fakeStoreProductDTO.getTitle());
        return genericProductDTO;
    }
    @Override
    public GenericProductDTO createProduct(GenericProductDTO product) {

        return fakeStoreProductServiceClient.createProduct(product);
    }
    @Override
    public GenericProductDTO getProductById(Long id) throws NotFoundException {
        FakeStoreProductDTO fakeStoreProductDTO = fakeStoreProductServiceClient.getProductById(id);
        return convertFakeStoreProductIntoGenericProduct(fakeStoreProductDTO);
    }

    @Override
    public void deleteById(Long id) throws NotFoundException {
        FakeStoreProductDTO fakeStoreProductDTO = fakeStoreProductServiceClient.deleteById(id);
        //return convertFakeStoreProductIntoGenericProduct(fakeStoreProductDTO);
    }
    @Override
    public List<GenericProductDTO> getAllProducts() {
        List<GenericProductDTO> genericProduct = new ArrayList<>();
        for(FakeStoreProductDTO product : fakeStoreProductServiceClient.getAllProducts()) {
            genericProduct.add(convertFakeStoreProductIntoGenericProduct(product));
        }
        return genericProduct;
    }

    @Override
    public GenericProductDTO updateProductById(GenericProductDTO updateProduct, Long id) throws NotFoundException {
        FakeStoreProductDTO fakeStoreProductDTO = fakeStoreProductServiceClient.updateProductById(updateProduct, id);
        return convertFakeStoreProductIntoGenericProduct(fakeStoreProductDTO);
    }

}
