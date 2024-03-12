package com.fiap.cp4.LibraryFiap.service;

import com.fiap.cp4.LibraryFiap.entity.Livro;
import com.fiap.cp4.LibraryFiap.repository.LivroRepository;
import com.fiap.cp4.LibraryFiap.strategy.ClassificadorStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;
    private ClassificadorStrategy classificadorStrategy;

    @Autowired
    public LivroService(ClassificadorStrategy classificadorStrategy) {
        this.classificadorStrategy = classificadorStrategy;
    }

    public Livro criarLivro(Livro livro) {
        return livroRepository.save(livro);
    }

    public List<Livro> listarLivros(ClassificadorStrategy strategy) { //ClassificadorStrategy Parâmetro
        List<Livro> livros = livroRepository.findAll();
        //A lista de livros e retorna a lista classificada de acordo com essa estratégia.
        return strategy.classify(livros);
    }

    public Livro encontrarLivroPorId(Long id) {
        return livroRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Livro não encontrado com o ID: " + id));
    }

    public void atualizarLivro(Livro livro) {
        livroRepository.save(livro);
    }

    public void removerLivro(Long id) {
        livroRepository.deleteById(id);
    }

}
