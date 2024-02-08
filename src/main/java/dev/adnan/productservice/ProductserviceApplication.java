package dev.adnan.productservice;


import dev.adnan.productservice.inheritance.tablePerClass.Mentor;
import dev.adnan.productservice.inheritance.tablePerClass.MentorRepository;
import dev.adnan.productservice.inheritance.tablePerClass.User;
import dev.adnan.productservice.inheritance.tablePerClass.UserRepository;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class ProductserviceApplication implements CommandLineRunner {

    private MentorRepository mentorRepository;
    private UserRepository userRepository;
    public ProductserviceApplication(@Qualifier("tpc_mr") MentorRepository mentorRepository,
                                     UserRepository userRepository) {
        this.mentorRepository = mentorRepository;
        this.userRepository = userRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(ProductserviceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Mentor mentor = new Mentor();
        mentor.setRating(4.5);
        mentor.setName("Adnan");
        mentor.setEmail("adnan12@gmail.com");
        mentorRepository.save(mentor);

        User user = new User();
        user.setEmail("randomemail@gmail.com");
        user.setName("Maddy");

        userRepository.save(user);
        //user.setId("");

        List<User> users = userRepository.findAll();
        users.stream().forEach(x -> System.out.println(x));
    }



}
