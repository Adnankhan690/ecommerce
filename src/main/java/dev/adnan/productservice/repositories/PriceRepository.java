package dev.adnan.productservice.repositories;

import dev.adnan.productservice.models.Price;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PriceRepository  extends JpaRepository<Price, Long> {

}
