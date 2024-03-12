package com.fiap.cp4.LibraryFiap.strategy;

import com.fiap.cp4.LibraryFiap.entity.Livro;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Component
public class ClassificarPorAno implements ClassificadorStrategy{

    //Aqui, a lista de livros é ordenada com base em um comparador.
    // O comparador utiliza o método getPublicacao() da classe Livro para comparar os objetos.
    // Além disso, o método reversed() é chamado, o que significa que a ordem da classificação
    // será reversa, ou seja, do mais recente para o mais antigo (decrescente).
    @Override
    public List<Livro> classify(List<Livro> livros) {
        Collections.sort(livros, Comparator.comparing(Livro::getPublicacao).reversed());
        return livros;
    }
}
