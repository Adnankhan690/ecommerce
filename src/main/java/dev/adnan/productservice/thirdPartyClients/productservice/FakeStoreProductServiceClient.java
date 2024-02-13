package dev.adnan.productservice.thirdPartyClients.productservice;

import dev.adnan.productservice.DTO.GenericProductDTO;
import dev.adnan.productservice.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/**
 * wrapper over fakeStoreProductService
 **/
@Service
public class FakeStoreProductServiceClient {
    @Value("%{fakestore.api.url}")
    private String fakeStoreApiUrl;

    @Value("${fakestore.api.paths.product}")
    private String fakeStoreProductApiUrl;

    private String baseProductRequestUrl;
    private String baseSpecificProductRequestUrl;
    RestTemplateBuilder restTemplateBuilder;

    public FakeStoreProductServiceClient(RestTemplateBuilder restTemplateBuilder,
                                         @Value("${fakestore.api.url}") String fakeStoreApiUrl,
                                         @Value("${fakestore.api.paths.product}") String fakeStoreProductApiUrl) {

        this.restTemplateBuilder = restTemplateBuilder;
        this.baseProductRequestUrl = fakeStoreApiUrl + fakeStoreProductApiUrl;
        this.baseSpecificProductRequestUrl = fakeStoreApiUrl + fakeStoreProductApiUrl + "/{id}";
    }
    public GenericProductDTO createProduct(GenericProductDTO product) {
        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<GenericProductDTO> response = restTemplate.postForEntity(baseProductRequestUrl,
                product, GenericProductDTO.class);

        return response.getBody();
    }

    public FakeStoreProductDTO getProductById(Long id) throws NotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO> response = restTemplate.getForEntity(baseSpecificProductRequestUrl,
                FakeStoreProductDTO.class, id);

        FakeStoreProductDTO fakeStoreProductDTO = response.getBody();
        //Exception
        if(fakeStoreProductDTO == null) {
            throw new NotFoundException("Product with id "+id+" doesn't exist");
        }
        return fakeStoreProductDTO;
    }

    public FakeStoreProductDTO deleteById(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();

        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDTO.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDTO>> responseExtractor =
                restTemplate.responseEntityExtractor(FakeStoreProductDTO.class);

        ResponseEntity<FakeStoreProductDTO> response = restTemplate.execute(baseSpecificProductRequestUrl, HttpMethod.DELETE,
                requestCallback, responseExtractor, id);

        return response.getBody();
    }

    public List<FakeStoreProductDTO> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO[]> response = restTemplate.getForEntity(baseProductRequestUrl,
                FakeStoreProductDTO[].class);

        List<FakeStoreProductDTO> productList = new ArrayList<>();

        //productList.addAll(Arrays.asList(response.getBody()));
        for(FakeStoreProductDTO productVar : response.getBody()) {
            productList.add(productVar);
        }
        return productList;
    }

    public FakeStoreProductDTO updateProductById(GenericProductDTO updateProduct, Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO> response = restTemplate.getForEntity(baseSpecificProductRequestUrl,
                FakeStoreProductDTO.class, id);

        FakeStoreProductDTO existingProduct = response.getBody();

        existingProduct.setCategory(Objects.requireNonNullElse(updateProduct.getCategory(),
                existingProduct.getCategory()));

        existingProduct.setDescription(Objects.requireNonNullElse(updateProduct.getDescription(),
                existingProduct.getDescription()));

        existingProduct.setImage(Objects.requireNonNullElse(updateProduct.getImage(),
                existingProduct.getImage()));

        existingProduct.setTitle(Objects.requireNonNullElse(updateProduct.getTitle(),
                existingProduct.getTitle()));

        return existingProduct;
    }
}
