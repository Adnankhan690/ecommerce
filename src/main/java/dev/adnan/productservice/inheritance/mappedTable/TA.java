package dev.adnan.productservice.inheritance.mappedTable;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name= "mt_TA")
public class TA extends User {
    private double rating;
}
