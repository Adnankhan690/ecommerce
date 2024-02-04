package dev.adnan.productservice.services;

import dev.adnan.productservice.DTO.FakeStoreProductDTO;
import dev.adnan.productservice.DTO.GenericProductDTO;
import dev.adnan.productservice.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService {
    RestTemplateBuilder restTemplateBuilder;
    private String getProductRequestUrl = "https://fakestoreapi.com/products/{id}";
    private String createProductUrl = "https://fakestoreapi.com/products";

    public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @Override
    public GenericProductDTO createProduct(GenericProductDTO product) {
        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<GenericProductDTO> response = restTemplate.postForEntity(createProductUrl,
                product, GenericProductDTO.class);

        return response.getBody();
    }


    @Override
    public GenericProductDTO getProductById(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        //Gets the response/calls the 3party APIs
        ResponseEntity<FakeStoreProductDTO> response =  restTemplate.getForEntity(getProductRequestUrl,
                FakeStoreProductDTO.class, id);
        //Then 3party APIs JSON is converted into our FakeStoreProductDTO
        FakeStoreProductDTO fakeStoreProductDTO = response.getBody();


        GenericProductDTO product = new GenericProductDTO();
        //Now we will forward this information from 3party API which is converted into FakeStoreDTO to our clients
        product.setImage(fakeStoreProductDTO.getImage());
        product.setDescription(fakeStoreProductDTO.getDescription());
        product.setCategory(fakeStoreProductDTO.getCategory());
        product.setTitle(fakeStoreProductDTO.getTitle());
        product.setPrice(fakeStoreProductDTO.getPrice());

        return product;
    }
}
