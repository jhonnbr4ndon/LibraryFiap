package com.fiap.cp4.LibraryFiap.controller;

import com.fiap.cp4.LibraryFiap.controller.dto.LivroDTO;
import com.fiap.cp4.LibraryFiap.entity.Livro;
import com.fiap.cp4.LibraryFiap.service.LivroService;
import com.fiap.cp4.LibraryFiap.service.mapper.LivroMapper;
import com.fiap.cp4.LibraryFiap.strategy.ClassificadorStrategy;
import com.fiap.cp4.LibraryFiap.strategy.ClassificarPorAno;
import com.fiap.cp4.LibraryFiap.strategy.ClassificarPorTitulo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livro")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @PostMapping
    public ResponseEntity<LivroDTO> criarLivro(@RequestBody LivroDTO livroDTO) {
        Livro respostaLivro = livroService.criarLivro(LivroMapper.toEntity(livroDTO));
        return ResponseEntity.ok(LivroMapper.toDTO(respostaLivro));
    }

    @GetMapping
    public ResponseEntity<List<LivroDTO>> listarLivros() {
        //Aqui, uma nova instância da classe ClassificarPorTitulo é criada.
        //Isso cria uma estratégia de classificação específica para classificar os livros por título.
        ClassificadorStrategy strategy = new ClassificarPorTitulo();
        //O método listarLivros() do LivroService é chamado,
        //passando a estratégia de classificação como parâmetro.
        List<LivroDTO> listarLivros = livroService.listarLivros(strategy).stream().map(LivroMapper::toDTO).toList();
        //Em seguida, a lista de livros retornada pelo serviço é mapeada
        // para uma lista de LivroDTO usando o método toDTO da classe LivroMapper
        return ResponseEntity.ok(listarLivros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivroDTO> encontrarLivroPorId(@PathVariable Long id) {
        Livro livro = livroService.encontrarLivroPorId(id);
        return ResponseEntity.ok(LivroMapper.toDTO(livro));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LivroDTO> atualizarLivro(@PathVariable Long id, @RequestBody LivroDTO livroDTO) {
        Livro livro = LivroMapper.toEntity(livroDTO);
        livro.setId(id);
        livroService.atualizarLivro(livro);
        return ResponseEntity.ok(LivroMapper.toDTO(livro));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerLivro(@PathVariable Long id) {
        livroService.removerLivro(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/ano-publicacao")
    public ResponseEntity<List<LivroDTO>> listarLivrosPorAno() {
        //Aqui, uma nova instância da classe ClassificarPorAno é criada,
        //que é uma implementação específica de ClassificadorStrategy.
        //Esta estratégia de classificação é usada para classificar os livros por ano de publicação.
        ClassificadorStrategy strategy = new ClassificarPorAno();
        //O método listarLivros() do LivroService é chamado,
        // passando a estratégia de classificação por ano como parâmetro.
        List<LivroDTO> listarLivros = livroService.listarLivros(strategy).stream().map(LivroMapper::toDTO).toList();
        return ResponseEntity.ok(listarLivros);
    }
}
