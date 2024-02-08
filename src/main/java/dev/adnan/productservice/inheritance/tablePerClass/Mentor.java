package dev.adnan.productservice.inheritance.tablePerClass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity(name = "tbp_mentor")
public class Mentor extends User {
    private double rating;
}
