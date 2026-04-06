package com.accenture.desafio_crud_cache.repository;

import com.accenture.desafio_crud_cache.model.Produto;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProdutoRepository extends MongoRepository<Produto, Long> {
}
