package com.mathues.pagamento.modules.venda.service.impl;

import com.mathues.pagamento.PagamentoApplication;
import com.mathues.pagamento.exception.ResourceNotFoundException;
import com.mathues.pagamento.model.ProdutoVenda;
import com.mathues.pagamento.model.Venda;
import com.mathues.pagamento.modules.fake.FakeProdutoVenda;
import com.mathues.pagamento.modules.fake.FakeVenda;
import com.mathues.pagamento.modules.produtovenda.service.impl.ProdutoVendaRepository;
import com.mathues.pagamento.modules.venda.service.VendaService;
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

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(
        classes = PagamentoApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public class VendaServiceImplTest {

    @MockBean
    private VendaRepository vendaRepository;

    @MockBean
    private ProdutoVendaRepository produtoVendaRepository;

    @Autowired
    private VendaService service;

    @Test
    void shouldCreateVenda() {

        final var venda = FakeVenda.generate();
        final var vendaRequest = FakeVenda.generateVendaRequest();
        final var produtoVenda = FakeProdutoVenda.generate();

        when(this.vendaRepository.save(any(Venda.class))).thenReturn(venda);
        when(this.produtoVendaRepository.save(any(ProdutoVenda.class))).thenReturn(produtoVenda);

        final var response = this.service.create(vendaRequest);

        assertNotNull(response);
        assertEquals(response.getId(), venda.getId());
        assertEquals(response.getProdutos(), venda.getProdutos());
        assertEquals(response.getData(), venda.getData());
        assertEquals(response.getValorTotal(), venda.getValorTotal());
        assertFalse(response.getProdutos().isEmpty());
        assertEquals(response.getProdutos().size(), 1);

        verify(this.vendaRepository, times(1)).save(any(Venda.class));
        verify(this.produtoVendaRepository, times(1)).save(any(ProdutoVenda.class));
    }

    @Test
    void shouldFindAll() {

        final Page<Venda> page = new PageImpl<>(List.of(FakeVenda.generate()));
        final var pageable = PageRequest.of(0, 1);

        when(this.vendaRepository.findAll(any(Pageable.class))).thenReturn(page);

        final var response = this.service.findAll(pageable);

        assertNotNull(response);

        verify(this.vendaRepository, times(1)).findAll(any(Pageable.class));
    }

    @Test
    void shouldFindByIdVenda() {

        final var venda = FakeVenda.generate();

        when(this.vendaRepository.findById(anyLong())).thenReturn(Optional.of(venda));

        final var response = this.service.findById(1L);

        assertNotNull(response);
        assertEquals(response.getId(), venda.getId());
        assertEquals(response.getProdutos(), venda.getProdutos());
        assertEquals(response.getData(), venda.getData());
        assertEquals(response.getValorTotal(), venda.getValorTotal());

        verify(this.vendaRepository, times(1)).findById(anyLong());
    }

    @Test
    void shouldFindByIdVendaWhenResourceNotFoundExceptionExist() {

        when(this.vendaRepository.findById(anyLong())).thenReturn(Optional.empty());

        final var exception = assertThrows(ResourceNotFoundException.class,
                () -> this.service.findById(1L),
                "Deve retornar um ResourceNotFoundException!");

        assertNotNull(exception);
        assertTrue(exception.getMessage().contains("No records found for this ID"));

        verify(this.vendaRepository, times(1)).findById(anyLong());
    }
}
