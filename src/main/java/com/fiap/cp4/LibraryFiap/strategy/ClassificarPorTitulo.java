package com.fiap.cp4.LibraryFiap.strategy;

import com.fiap.cp4.LibraryFiap.entity.Livro;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Primary
@Component
public class ClassificarPorTitulo implements ClassificadorStrategy {

    //Aqui, o método sorted() é chamado no fluxo de livros.
    // Ele ordena os elementos do fluxo com base no comparador fornecido.
    // O comparador é definido usando Comparator.comparing(), que compara
    // os objetos Livro com base em seus títulos. Portanto, os livros serão
    // classificados em ordem alfabética pelo título.
    //
    //.collect(Collectors.toList()): Por fim, collect() é chamado para coletar
    // os elementos do fluxo em uma lista. Collectors.toList() é um coletor que
    // coleta os elementos do fluxo em uma nova lista.
    @Override
    public List<Livro> classify(List<Livro> livros) {
        return livros.stream()
                .sorted(Comparator.comparing(Livro::getTitulo))
                .collect(Collectors.toList());
    }
}
