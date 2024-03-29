package dev.adnan.productservice.thirdPartyClients.productservice;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDTO {
    private Long id;
    private String title;
    private int price;
    private String description;
    private String image;
    private String category;
}
