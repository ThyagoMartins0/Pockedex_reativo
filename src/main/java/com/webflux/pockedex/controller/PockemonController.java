package com.webflux.pockedex.controller;

import com.webflux.pockedex.model.PockemonEvent;
import com.webflux.pockedex.model.PockemonModel;
import com.webflux.pockedex.repository.PockemonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
@RestController
@RequestMapping("/pockemon")
public class PockemonController {
    private PockemonRepository repository;
    public PockemonController(PockemonRepository repository){
        this.repository = repository;
    }

    @GetMapping
    public Flux<PockemonModel> getAllPockemons(){
        return repository.findAll();
    }

    @GetMapping("/{id}")
        public Mono<ResponseEntity<PockemonModel>> getPockemon(@PathVariable(value = "id") String id){
        return repository.findById(id)
                .map(savedPockemon -> ResponseEntity.ok(savedPockemon))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<PockemonModel> savePockemon (@RequestBody PockemonModel pockemonModel){
        return repository.save(pockemonModel);
    }

    @PutMapping("{id}")
    public Mono<ResponseEntity<PockemonModel>> updatePockemon (@PathVariable(value = "id")
                                                               String id,
                                                               @RequestBody PockemonModel pockemonModel){
        return repository.findById(id)
                .flatMap(existingPockemon -> {
                    existingPockemon.setName(pockemonModel.getName());
                    existingPockemon.setCategoria(pockemonModel.getCategoria());
                    existingPockemon.setHabilidade(pockemonModel.getHabilidade());
                    existingPockemon.setPeso(pockemonModel.getPeso());
                    return repository.save(existingPockemon);
                })
                .map(updatePockemon -> new ResponseEntity<>(updatePockemon, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
@DeleteMapping ("{id}")
    public Mono<ResponseEntity<Void>> deletePockemon(@PathVariable(value = "id") String id){
        return repository.findById(id)
                .flatMap(existingPockemon ->
                        repository.delete(existingPockemon)
                        .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
                )
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping
    public Mono<Void> deleteAllPockemons(){
        return repository.deleteAll();
    }

    @GetMapping(value = "/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public  Flux<PockemonEvent> getPockemonEvent(){
        return Flux.interval (Duration.ofSeconds(5))
                .map(val ->
                        new PockemonEvent(val, "Product Event")
                );
    }



}
