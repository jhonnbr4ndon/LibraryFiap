package com.fiap.cp4.LibraryFiap;

import com.fiap.cp4.LibraryFiap.controller.LivroController;
import com.fiap.cp4.LibraryFiap.controller.dto.LivroDTO;
import com.fiap.cp4.LibraryFiap.entity.Livro;
import com.fiap.cp4.LibraryFiap.service.LivroService;
import com.fiap.cp4.LibraryFiap.service.mapper.LivroMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class LivroControllerTest {

    //Isso cria um mock (simulação) do serviço LivroService para
    //uso nos testes. Isso permite que os métodos do serviço sejam
    //simulados e seus comportamentos sejam definidos durante os testes.
    @Mock
    private LivroService livroService;

    // Isso injeta automaticamente o mock do LivroService no LivroController
    //durante os testes. Isso permite que o controller seja testado isoladamente,
    //sem a necessidade de instanciar o serviço real.
    @InjectMocks
    private LivroController livroController;

    //void criarLivro() {...}: Este é um método de teste que verifica o comportamento
    //do método criarLivro() do LivroController quando todas as informações necessárias
    //são fornecidas no objeto LivroDTO. Ele cria um objeto LivroDTO, configura o comportamento
    //esperado do método criarLivro() do serviço usando o mock do LivroService, chama o método a
    //ser testado e verifica se o comportamento esperado foi alcançado.

    @Test
    void criarLivro() {
        LivroDTO livroDTO = new LivroDTO();
        //Um objeto LivroDTO é criado e preenchido com informações completas,
        //incluindo título, autor, data de publicação e categoria.
        livroDTO.setTitulo("Livro Teste");
        livroDTO.setAutor("Autor Teste");
        livroDTO.setPublicacao(new Date(System.currentTimeMillis()));
        livroDTO.setCategoria("Categoria Teste");

        Livro livro = LivroMapper.toEntity(livroDTO);
        //O comportamento do método criarLivro() do LivroService é configurado usando o when() do Mockito.
        when(livroService.criarLivro(any(Livro.class))).thenReturn(livro);

        //O método criarLivro() do LivroController é chamado,
        // passando o objeto LivroDTO criado anteriormente como argumento.
        ResponseEntity<LivroDTO> responseEntity = livroController.criarLivro(livroDTO);

        //Verifica se o método criarLivro() do LivroService foi chamado
        // exatamente uma vez com qualquer objeto Livro como argumento.
        verify(livroService, times(1)).criarLivro(any(Livro.class));

        //erifica se o código de status retornado pela resposta é HttpStatus.OK.
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        //Verifica se o corpo da resposta não é nulo.
        assertNotNull(responseEntity.getBody());

        //Verifica se o título do livro retornado na resposta corresponde ao título definido no objeto LivroDTO.
        assertEquals(livroDTO.getTitulo(), responseEntity.getBody().getTitulo());
    }

    //void criarLivroInformacoesIncompletas() {...}: Este é outro método de teste que verifica
    //o comportamento do método criarLivro() do LivroController quando informações incompletas
    //são fornecidas no objeto LivroDTO. Ele cria um objeto LivroDTO com informações incompletas,
    //chama o método a ser testado e verifica se o comportamento esperado foi alcançado.
    @Test
    void criarLivroInformacoesIncompletas() {
        LivroDTO livroDTO = new LivroDTO();
        //Um objeto LivroDTO é criado, mas apenas algumas informações são
        //definidas (como título, data de publicação e categoria).
        livroDTO.setTitulo("Livro Teste");
        livroDTO.setPublicacao(new Date(System.currentTimeMillis()));
        livroDTO.setCategoria("Categoria Teste");

        //O método criarLivro() do LivroController é chamado, passando
        // o objeto LivroDTO criado anteriormente como argumento.
        ResponseEntity<LivroDTO> responseEntity = livroController.criarLivro(livroDTO);

        //verify(livroService, never()).criarLivro(any(Livro.class)): Verifica se o
        //método criarLivro() do LivroService nunca foi chamado com qualquer objeto Livro como argumento.
        verify(livroService, never()).criarLivro(any(Livro.class));

        //assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode()): Verifica
        //se o código de status retornado pela resposta é HttpStatus.BAD_REQUEST.
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());

        //assertNull(responseEntity.getBody()): Verifica se o corpo da resposta
        //é nulo, indicando que nenhum livro foi criado com informações incompletas.
        assertNull(responseEntity.getBody());
    }
}
