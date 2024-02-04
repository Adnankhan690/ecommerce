package dev.adnan.productservice.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDTO {
    private String title;
    private int price;
    private String description;
    private String image;
    private String category;
}
