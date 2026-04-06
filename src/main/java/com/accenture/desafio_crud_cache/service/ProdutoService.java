package com.accenture.desafio_crud_cache.service;

import com.accenture.desafio_crud_cache.config.SequenceGeneratorService;
import com.accenture.desafio_crud_cache.dto.ProdutoDTO;
import com.accenture.desafio_crud_cache.model.Produto;
import com.accenture.desafio_crud_cache.repository.ProdutoRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private static final String SEQUENCE_NAME = "produto_sequence";

    private final ProdutoRepository repository;
    private final SequenceGeneratorService sequenceGenerator;

    public ProdutoService(ProdutoRepository repository,
                          SequenceGeneratorService sequenceGenerator) {
        this.repository = repository;
        this.sequenceGenerator = sequenceGenerator;
    }

    public Produto criar(ProdutoDTO dto) {
        Produto produto = new Produto();
        produto.setId(sequenceGenerator.generateSequence(SEQUENCE_NAME));
        produto.setNome(dto.getNome());
        produto.setDescricao(dto.getDescricao());
        produto.setPreco(dto.getPreco());

        return repository.save(produto);
    }

    @Cacheable(value = "produtos")
    public List<Produto> listar() {
        return repository.findAll();
    }

    @CacheEvict(value = "produtos", allEntries = true)
    public Produto atualizar(Long id, ProdutoDTO dto) {
        Produto produto = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        produto.setNome(dto.getNome());
        produto.setDescricao(dto.getDescricao());
        produto.setPreco(dto.getPreco());

        return repository.save(produto);
    }

    @CacheEvict(value = "produtos", allEntries = true)
    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
