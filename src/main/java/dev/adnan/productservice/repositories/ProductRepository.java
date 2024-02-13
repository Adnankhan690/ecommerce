package dev.adnan.productservice.repositories;

import dev.adnan.productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByTitleEquals(String title);
    Optional<Product> findById(Long id);
//    Optional<Product> findByCategoryLike(String categoryName);
//    List<Product> findAllByCategoryLike(String categoryName);
    Optional<Product> findByCategory_Name(String categoryName);
    List<Product> findAllByCategory_Name(String categoryName);
    Product findByTitleEqualsAndPrice_Price(String title, double price);
    List<Product> findAllByPrice_Currency(String currency);

    long countAllByPrice_Currency(String currency);


    void delete(Product entity);
    //Written in SQL, does not use Compile time check
    @Query(value = CustomQueries.FIND_ALL_BY_TITLE,nativeQuery = true)
    List<Product> findAllByTitle(String title);

    //Written in HQL(Hibernate Query Language), uses Compile Time Check
//    @Query("select product from product where product.title = :title")
//    List<Product> readAllByTitle(String title);
}
