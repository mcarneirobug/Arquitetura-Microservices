package com.matheus.crud.model.produto.service.impl;

import com.matheus.crud.CrudApplication;
import com.matheus.crud.exception.ResourceNotFoundException;
import com.matheus.crud.fake.FakeProduto;
import com.matheus.crud.model.Produto;
import com.matheus.crud.modules.produto.service.ProdutoService;
import com.matheus.crud.modules.produto.service.impl.ProdutoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@SpringBootTest(
        classes = CrudApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public class ProdutoServiceImplTest {

    @MockBean
    private ProdutoRepository repository;

    @Autowired
    private ProdutoService service;

    @Test
    void shouldCreate() {

        final var produtoRequest = FakeProduto.generateProdutoRequest();
        final var produto = FakeProduto.generate();

        when(this.repository.save(any(Produto.class))).thenReturn(produto);

        final var response = this.service.create(produtoRequest);

        assertNotNull(response);
        assertEquals(response.getId(), produto.getId());
        assertEquals(response.getNome(), produto.getNome());
        assertEquals(response.getEstoque(), produto.getEstoque());
        assertEquals(response.getPreco(), produto.getPreco());

        verify(this.repository, times(1)).save(any(Produto.class));
    }

    @Test
    void shouldReturnPageWhenFindAllProduto() {

        final Page<Produto> page = new PageImpl<>(List.of(FakeProduto.generate()));
        final var pageable = PageRequest.of(0, 1);

        when(this.repository.findAll(any(Pageable.class))).thenReturn(page);

        final var response = this.service.findAll(pageable);

        assertNotNull(response);

        verify(this.repository, times(1)).findAll(any(Pageable.class));
    }

    @Test
    void shouldFindById() {

        final var produto = FakeProduto.generate();

        when(this.repository.findById(anyLong())).thenReturn(Optional.of(produto));

        final var response = this.service.findById(1L);

        assertNotNull(response);
        assertEquals(response.getId(), produto.getId());
        assertEquals(response.getNome(), produto.getNome());
        assertEquals(response.getEstoque(), produto.getEstoque());
        assertEquals(response.getPreco(), produto.getPreco());

        verify(this.repository, times(1)).findById(anyLong());
    }

    @Test
    void shouldFindByIdWhenNotFoundExceptionExist() {

        when(this.repository.findById(anyLong())).thenReturn(Optional.empty());

        final var exception = assertThrows(ResourceNotFoundException.class,
                () -> this.service.findById(1L),
                "Deve retornar um ResourceNotFoundException!");

        assertNotNull(exception);
        assertTrue(exception.getMessage().contains("No records found for this ID"));

        verify(this.repository, times(1)).findById(anyLong());
    }

    @Test
    void shouldUpdate() {

        final var produto = FakeProduto.generate();
        final var produtoRequest = FakeProduto.generateProdutoRequest();

        when(this.repository.findById(anyLong())).thenReturn(Optional.of(produto));
        when(this.repository.save(any(Produto.class))).thenReturn(produto);

        final var response = this.service.update(produtoRequest, 1L);

        assertNotNull(response);
        assertEquals(response.getId(), produto.getId());
        assertEquals(response.getNome(), produto.getNome());
        assertEquals(response.getEstoque(), produto.getEstoque());
        assertEquals(response.getPreco(), produto.getPreco());

        verify(this.repository, times(1)).findById(anyLong());
        verify(this.repository, times(1)).save(any(Produto.class));
    }

    @Test
    void shouldUpdateWhenResourceNotFoundExceptionExist() {

        final var produto = FakeProduto.generate();
        final var produtoRequest = FakeProduto.generateProdutoRequest();

        when(this.repository.findById(anyLong())).thenReturn(Optional.empty());
        when(this.repository.save(any(Produto.class))).thenReturn(produto);

        final var exception = assertThrows(ResourceNotFoundException.class,
                () -> this.service.update(produtoRequest, 1L),
                "Deve retornar um ResourceNotFoundException!");

        assertNotNull(exception);
        assertTrue(exception.getMessage().contains("No records found for this ID"));

        verify(this.repository, times(1)).findById(anyLong());
        verify(this.repository, times(0)).save(any(Produto.class));
    }

    @Test
    void shouldDelete() {

        final var produto = FakeProduto.generate();

        when(this.repository.findById(anyLong())).thenReturn(Optional.of(produto));
        doNothing().when(this.repository).delete(any(Produto.class));

        this.service.delete(1L);

        verify(this.repository, times(1)).findById(anyLong());
        verify(this.repository, times(1)).delete(any(Produto.class));
    }

    @Test
    void shouldDeleteWhenResourceNotFoundExceptionExist() {

        when(this.repository.findById(anyLong())).thenReturn(Optional.empty());
        doNothing().when(this.repository).delete(any(Produto.class));

        final var exception = assertThrows(ResourceNotFoundException.class,
                () -> this.service.delete(1L),
                "Deve retornar um ResourceNotFoundException!");

        verify(this.repository, times(1)).findById(anyLong());
        verify(this.repository, times(0)).delete(any(Produto.class));

    }
}
