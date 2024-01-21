package com.webflux.pockedex.repository;

import com.webflux.pockedex.model.PockemonModel;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface PockemonRepository extends ReactiveMongoRepository<PockemonModel, String> {


}