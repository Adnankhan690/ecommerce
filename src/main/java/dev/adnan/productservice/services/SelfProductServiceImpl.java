package dev.adnan.productservice.services;

import dev.adnan.productservice.DTO.GenericProductDTO;
import dev.adnan.productservice.exceptions.NotFoundException;
import dev.adnan.productservice.models.Category;
import dev.adnan.productservice.models.Price;
import dev.adnan.productservice.models.Product;
import dev.adnan.productservice.repositories.CategoryRepository;
import dev.adnan.productservice.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;

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
    private CategoryRepository categoryRepository;

    public SelfProductServiceImpl(ProductRepository productRepository,
                                  RestTemplateBuilder restTemplateBuilder,
                                  @Value("${fakestore.api.url}") String productStoreApriUrl,
                                  @Value("/products") String baseProductRequestUrl,
                                  CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
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
    public void deleteById(Long id) throws NotFoundException {
        Optional<Product> product = productRepository.findById(id);

        if(product.isEmpty()) {
            throw new NotFoundException("Product with id "+id+" doesn't exist");
        }

        productRepository.deleteById(id);
    }

    @Override
    public List<GenericProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<GenericProductDTO> genericList = new ArrayList<>();

        for(Product product : products) {
            GenericProductDTO genericProduct = convertProductToGenericProductDto(product);
            genericList.add(genericProduct);
        }
        return genericList;
    }

    public List<GenericProductDTO> getProductsInCategory(String categoryName) throws NotFoundException {
        Optional<Product> category = productRepository.findByCategory_Name(categoryName);
        if(category.isEmpty())  {
            throw new NotFoundException("Product with category name "+categoryName+" doesn't exist");
        }
        List<Product> products = productRepository.findAllByCategory_Name(categoryName);

        List<GenericProductDTO> genericProductList = new ArrayList<>();
        for(Product product : products) {
            GenericProductDTO genericProduct = convertProductToGenericProductDto(product);
            genericProductList.add(genericProduct);
        }

        return genericProductList;
    }
}
