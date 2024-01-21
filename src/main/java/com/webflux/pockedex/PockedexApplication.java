package com.webflux.pockedex;

import com.webflux.pockedex.model.PockemonModel;
import com.webflux.pockedex.repository.PockemonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.ReactiveMongoClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class PockedexApplication {

	public static void main(String[] args) {
		SpringApplication.run(PockedexApplication.class, args);}

	@Bean
	CommandLineRunner init(ReactiveMongoOperations operations, PockemonRepository repository){
		return args ->{
			Flux<PockemonModel>pockemonModelFlux= Flux.just(
				new PockemonModel(null, "Bulbassauro", "Semente", "OverGrow", 6.09),
					new PockemonModel(null, "Charizard", "Fogo", "Blaze", 90.05),
					new PockemonModel(null, "Caterpie", "Minhoca", "Poeira do Escudo", 2.09),
					new PockemonModel(null, "Blastoise", "Marisco", "Torrente", 6.09))
					.flatMap(repository::save);

			pockemonModelFlux
					.thenMany(repository.findAll())
					.subscribe(System.out::println);
				};
			}
		}
