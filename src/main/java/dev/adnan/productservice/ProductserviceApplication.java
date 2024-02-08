package dev.adnan.productservice;


import dev.adnan.productservice.inheritance.tablePerClass.Mentor;
import dev.adnan.productservice.inheritance.tablePerClass.MentorRepository;
import dev.adnan.productservice.inheritance.tablePerClass.User;
import dev.adnan.productservice.inheritance.tablePerClass.UserRepository;


import dev.adnan.productservice.models.Category;
import dev.adnan.productservice.models.Product;
import dev.adnan.productservice.repositories.CategoryRepository;
import dev.adnan.productservice.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.UUID;

@SpringBootApplication
public class ProductserviceApplication implements CommandLineRunner {

    private MentorRepository mentorRepository;
    private UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

//    public ProductserviceApplication(@Qualifier("tpc_mr") MentorRepository mentorRepository,
//                                     UserRepository userRepository,
//                                     ProductRepository productRepository) {
//        this.mentorRepository = mentorRepository;
//        this.userRepository = userRepository;
//        this.productRepository = productRepository;
//    }

      public ProductserviceApplication(CategoryRepository categoryRepository,
                                       ProductRepository productRepository) {
          this.categoryRepository = categoryRepository;
          this.productRepository = productRepository;
      }

    public static void main(String[] args) {
        SpringApplication.run(ProductserviceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        Mentor mentor = new Mentor();
//        mentor.setRating(4.5);
//        mentor.setName("Adnan");
//        mentor.setEmail("adnan12@gmail.com");
//        mentorRepository.save(mentor);
//
//        User user = new User();
//        user.setEmail("randomemail@gmail.com");
//        user.setName("Maddy");
//
//        userRepository.save(user);
//        //user.setId("");
//
//        List<User> users = userRepository.findAll();
//        users.stream().forEach(x -> System.out.println(x));

        Category category = new Category();
        category.setName("Apple Device");
        Category savedCategory = categoryRepository.save(category);

        Product product = new Product();
        product.setDescription("The most latest iphone 15, launching with quad camera.");
        product.setTitle("iPhone 15");
        product.setPrice(100000.00);

        product.setCategory(category);

        productRepository.save(product);

        Category category1 = categoryRepository.findById(UUID.fromString(
                "70758478-2433-49d9-a733-a540dc3e6c13")).get();
        System.out.println("Category name is: "+category1.getName());

    }

}
