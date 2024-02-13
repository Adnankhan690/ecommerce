package dev.adnan.productservice.repositories;

import dev.adnan.productservice.models.Category;
import dev.adnan.productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
//    Optional<Product> findByCategoryLike(String categoryName);
//    List<Product> findAllByCategoryLike(String categoryName);

}
