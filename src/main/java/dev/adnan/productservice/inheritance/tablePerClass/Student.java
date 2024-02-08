package dev.adnan.productservice.inheritance.tablePerClass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "tbp_student")
public class Student extends User {
    private double psp;
    private double attendance;
}
