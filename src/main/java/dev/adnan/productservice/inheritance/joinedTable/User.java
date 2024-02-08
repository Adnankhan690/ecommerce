package dev.adnan.productservice.inheritance.joinedTable;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Inheritance(strategy = InheritanceType.JOINED)
@Entity(name = "jt_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;
}
