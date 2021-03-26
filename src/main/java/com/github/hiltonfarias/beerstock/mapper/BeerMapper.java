package com.github.hiltonfarias.beerstock.mapper;

import com.github.hiltonfarias.beerstock.dto.BeerDTO;
import com.github.hiltonfarias.beerstock.entity.Beer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BeerMapper {

    BeerMapper INSTANCE = Mappers.getMapper(BeerMapper.class);
    Beer toModel(BeerDTO beerDTO);
    BeerDTO toDTO(Beer beer);
}
