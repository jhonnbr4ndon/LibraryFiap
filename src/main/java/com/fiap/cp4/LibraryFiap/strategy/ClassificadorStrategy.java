package com.fiap.cp4.LibraryFiap.strategy;

import com.fiap.cp4.LibraryFiap.entity.Livro;

import java.util.List;

public interface ClassificadorStrategy {

    //Método classify, que recebe uma lista de Livro e retorna uma lista de Livro.
    List<Livro> classify(List<Livro> livros);
}
