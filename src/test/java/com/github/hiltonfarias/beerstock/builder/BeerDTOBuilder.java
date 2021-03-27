package com.github.hiltonfarias.beerstock.builder;

import com.github.hiltonfarias.beerstock.dto.BeerDTO;
import com.github.hiltonfarias.beerstock.enums.BeerType;
import lombok.Builder;

@Builder
public class BeerDTOBuilder {

    @Builder.Default
    private final Long id = 1L;

    @Builder.Default
    private final String name = "Brahma";

    @Builder.Default
    private final String brand = "Ambev";

    @Builder.Default
    private final int max = 50;

    @Builder.Default
    private final int quantity = 10;

    @Builder.Default
    private final BeerType beerType = BeerType.LAGER;

    public BeerDTO toBeerDTO() {
        return new BeerDTO(id, name, brand, max, quantity, beerType);
    }
}
