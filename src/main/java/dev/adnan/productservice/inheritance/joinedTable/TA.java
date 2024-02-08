package dev.adnan.productservice.inheritance.joinedTable;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "jt_TA")
@PrimaryKeyJoinColumn(name = "user_id")
public class TA extends User {
    private double rating;
}
