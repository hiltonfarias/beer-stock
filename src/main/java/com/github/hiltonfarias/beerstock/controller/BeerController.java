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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/beers")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BeerController implements BeerControllerDocs {

    private final BeerService beerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BeerDTO createBeer(@RequestBody @Valid BeerDTO beerDTO) throws BeerAlreadyRegisteredException {
        return beerService.createBeer(beerDTO);
    }

    @GetMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public BeerDTO findByName(@PathVariable String name) throws BeerNotFoundException {
        return beerService.findByName(name);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<BeerDTO> listBeers() {
        return beerService.listAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws BeerNotFoundException {
        beerService.deleteById(id);
    }

    @PatchMapping("/{id}/increment")
    public BeerDTO increment(
            @PathVariable Long id,
            @RequestBody @Valid QuantityDTO quantityDTO
    ) throws BeerNotFoundException, BeerStockExceededException {
        return beerService.increment(id, quantityDTO.getQuantity());
    }

    @PatchMapping("/{id}/decrement")
    public BeerDTO decrement(
            @PathVariable Long id,
            @RequestBody @Valid QuantityDTO quantityDTO) throws BeerNotFoundException, BeerStockExceededException {
        return beerService.decrement(id, quantityDTO.getQuantity());
    }
}
