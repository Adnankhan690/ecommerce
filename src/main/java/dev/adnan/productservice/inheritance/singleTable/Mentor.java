package dev.adnan.productservice.inheritance.singleTable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@Entity(name = "st_mentor")
@DiscriminatorValue(value = "1")
public class Mentor extends User {
    private double rating;
}
