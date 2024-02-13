package dev.adnan.productservice.services;

import dev.adnan.productservice.DTO.GenericProductDTO;
import dev.adnan.productservice.exceptions.NotFoundException;
import dev.adnan.productservice.models.Category;
import dev.adnan.productservice.models.Price;
import dev.adnan.productservice.models.Product;
import dev.adnan.productservice.repositories.ProductRepository;
import dev.adnan.productservice.thirdPartyClients.productservice.FakeStoreProductDTO;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service("selfProductServiceImpl")
public class SelfProductServiceImpl implements ProductService {
    @Value("${fakestore.api.url}")
    private String storeApiUrl;

    @Value("/products")
    private String productStoreApriUrl;

    private final String baseProductRequestUrl;
    private String baseSpecificProductRequestUrl;
    private ProductRepository productRepository;
    private RestTemplateBuilder restTemplateBuilder;

    public SelfProductServiceImpl(ProductRepository productRepository,
                                  RestTemplateBuilder restTemplateBuilder,
                                  @Value("${fakestore.api.url}") String productStoreApriUrl,
                                  @Value("/products") String baseProductRequestUrl) {
        this.productRepository = productRepository;
        this.restTemplateBuilder = restTemplateBuilder;
        this.baseProductRequestUrl = productStoreApriUrl + baseProductRequestUrl;
        this.baseSpecificProductRequestUrl = productStoreApriUrl + baseProductRequestUrl + "/{id}";
    }

    public GenericProductDTO convertProductToGenericProductDto(Product product) {
        GenericProductDTO genericProductDTO = new GenericProductDTO();

        genericProductDTO.setId(product.getId());
        genericProductDTO.setImage(product.getImage());
        genericProductDTO.setDescription(product.getDescription());
        genericProductDTO.setTitle(product.getTitle());
        genericProductDTO.setPrice(product.getPrice().getPrice());
        genericProductDTO.setCategory(product.getCategory().getName());

        return genericProductDTO;
    }
    @Override
    public GenericProductDTO getProductById(Long id) throws NotFoundException {
        Optional<Product> optionalProduct = productRepository.findById(id);

        if(optionalProduct.isEmpty()) {
            throw new NotFoundException("Product with id "+id+" doesn't exist");
        }
        Product product = optionalProduct.get();
        return convertProductToGenericProductDto(product);

    }

    @Override
    public GenericProductDTO createProduct(GenericProductDTO product) {
        Product newProduct = new Product();
        Category category = new Category();
        category.setName(product.getCategory());

        newProduct.setCategory(category);
        newProduct.setImage(product.getImage());
        newProduct.setDescription(product.getDescription());
        newProduct.setTitle(product.getTitle());
         Price price = new Price();
         price.setPrice(product.getPrice());
         newProduct.setPrice(price);
         //This is more good bcs of synchronization
         productRepository.saveAndFlush(newProduct);
//       productRepository.save(newProduct);

        return convertProductToGenericProductDto(newProduct);
    }

    @Override
    public GenericProductDTO updateProductById(GenericProductDTO updateProduct, Long id) throws NotFoundException {
        Optional<Product> product = productRepository.findById(id);
        if(product.isEmpty()) {
            throw new NotFoundException("Product with id "+id+" doesn't exist");
        }

        Product existingProduct = product.get();

        Price existingPrice = new Price();
        existingPrice.setPrice(existingProduct.getPrice().getPrice());

        Price updatePrice = new Price();
        updatePrice.setPrice(updateProduct.getPrice());

        existingProduct.setPrice(Objects.requireNonNullElse(updatePrice, existingPrice) );
        existingProduct.setImage(Objects.requireNonNullElse( updateProduct.getImage(), existingProduct.getImage()));
        existingProduct.setDescription(Objects.requireNonNullElse( updateProduct.getDescription(),
                existingProduct.getDescription()));
        existingProduct.setTitle(Objects.requireNonNullElse(updateProduct.getTitle(), existingProduct.getTitle()));

        Category updateCategory = new Category();
        updateCategory.setName(updateProduct.getCategory());

        Category existingCategory = new Category();
        existingCategory.setName(existingProduct.getCategory().getName());

        existingProduct.setCategory(Objects.requireNonNullElse( updateCategory, existingCategory));
        //Save it to DB
        productRepository.saveAndFlush(existingProduct);

        return convertProductToGenericProductDto(existingProduct);
    }

    @Override
    public GenericProductDTO deleteById(Long id) {
        return null;
    }

    @Override
    public List<GenericProductDTO> getAllProducts() {
        List<Product> product = productRepository.findAll();
        List<GenericProductDTO> genericList = new ArrayList<>();

        for(Product pd : product) {
            GenericProductDTO genericProduct = convertProductToGenericProductDto(pd);
            genericList.add(genericProduct);
        }
        return genericList;
    }
}
