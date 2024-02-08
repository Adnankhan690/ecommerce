package dev.adnan.productservice.inheritance.singleTable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "st_TA")
@DiscriminatorValue(value = "2")
public class TA extends User {
    private double rating;
}
