package com.github.hiltonfarias.beerstock.controller;

import com.github.hiltonfarias.beerstock.dto.BeerDTO;
import com.github.hiltonfarias.beerstock.dto.QuantityDTO;
import com.github.hiltonfarias.beerstock.exception.BeerAlreadyRegisteredException;
import com.github.hiltonfarias.beerstock.exception.BeerNotFoundException;
import com.github.hiltonfarias.beerstock.exception.BeerStockExceededException;
import com.github.hiltonfarias.beerstock.service.BeerService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/beers")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BeerController implements BeerControllerDocs {

    private final BeerService beerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Override
    public BeerDTO createBeer(@RequestBody @Valid BeerDTO beerDTO) throws BeerAlreadyRegisteredException {
        return beerService.createBeer(beerDTO);
    }

    @GetMapping("/{name}")
    @Override
    public BeerDTO findByName(@PathVariable String name) throws BeerNotFoundException {
        return beerService.findByName(name);
    }

    @GetMapping
    @Override
    public List<BeerDTO> listBeers() {
        return beerService.listAll();
    }

    @DeleteMapping("/{id}")
    @Override
    public void deleteById(@PathVariable Long id) throws BeerNotFoundException {
        beerService.deleteById(id);
    }

    @PatchMapping("/{id}/increment")
    public BeerDTO increment(
            @PathVariable Long id,
            @RequestBody QuantityDTO quantityDTO
    ) throws BeerNotFoundException, BeerStockExceededException {
        return beerService.increment(id, quantityDTO.getQuantity());
    }
}
