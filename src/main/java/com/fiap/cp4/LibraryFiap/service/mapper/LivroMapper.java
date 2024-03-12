package com.fiap.cp4.LibraryFiap.service.mapper;

import com.fiap.cp4.LibraryFiap.controller.dto.LivroDTO;
import com.fiap.cp4.LibraryFiap.entity.Livro;
public class LivroMapper {

    //public static Livro toEntity(LivroDTO livroDTO): Este método estático recebe
    //um objeto LivroDTO como parâmetro e retorna um objeto Livro. Ele cria um novo
    //objeto Livro, define os atributos com os valores correspondentes do objeto LivroDTO
    //passado como parâmetro e retorna o objeto Livro resultante.
    //
    //public static LivroDTO toDTO(Livro entity): Este método estático recebe um objeto
    //Livro como parâmetro e retorna um objeto LivroDTO. Ele cria um novo objeto LivroDTO,
    //define os atributos com os valores correspondentes do objeto Livro passado como parâmetro
    //e retorna o objeto LivroDTO resultante.
    //
    //Esses métodos são úteis para converter objetos entre as camadas da aplicação, por
    //exemplo, converter objetos de entidade que representam dados do banco de dados em
    //objetos DTO para serem enviados como resposta em uma solicitação HTTP ou para serem
    //usados na camada de apresentação da aplicação. Isso ajuda a separar as preocupações
    //e manter uma estrutura limpa e organizada no código.
    public static Livro toEntity(LivroDTO livroDTO) {
        Livro livro = new Livro();
        livro.setTitulo(livroDTO.getTitulo());
        livro.setAutor(livroDTO.getAutor());
        livro.setPublicacao(livroDTO.getPublicacao());
        livro.setCategoria(livroDTO.getCategoria());
        return livro;
    }

    public static LivroDTO toDTO(Livro entity) {
        LivroDTO dto = new LivroDTO();
        dto.setTitulo(entity.getTitulo());
        dto.setAutor(entity.getAutor());
        dto.setPublicacao(entity.getPublicacao());
        dto.setCategoria(entity.getCategoria());
        return dto;
    }

}
