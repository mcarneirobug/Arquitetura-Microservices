package com.matheus.crud.modules.produto.controller;

import com.matheus.crud.modules.produto.mapper.ProdutoMapper;
import com.matheus.crud.modules.produto.request.ProdutoRequest;
import com.matheus.crud.modules.produto.response.ProdutoResponse;
import com.matheus.crud.modules.produto.service.ProdutoService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    private final ProdutoService produtoService;
    private final PagedResourcesAssembler<ProdutoResponse> assembler;

    public ProdutoController(ProdutoService produtoService, PagedResourcesAssembler<ProdutoResponse> assembler) {
        this.produtoService = produtoService;
        this.assembler = assembler;
    }

    @GetMapping(value = "/{id}", produces = {"application/json", "application/xml", "application/y-yaml"})
    public ProdutoResponse findById(@PathVariable("id") Long id) {
        final var produto = ProdutoMapper.toResponse(this.produtoService.findById(id));
        produto.add(linkTo(methodOn(ProdutoController.class).findById(id)).withSelfRel());
        return produto;
    }

    @GetMapping(produces = {"application/json", "application/xml", "application/y-yaml"})
    public ResponseEntity<?> findAll(@RequestParam(value = "page", defaultValue = "0") int page,
                                     @RequestParam(value = "limit", defaultValue = "12") int limit,
                                     @RequestParam(value = "direction", defaultValue = "asc") String direction) {
        var sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "nome"));
        final var produtos = this.produtoService.findAll(pageable);

        produtos.stream()
                .forEach(p -> p.add(linkTo(methodOn(ProdutoController.class).findById(p.getId())).withSelfRel()));

        PagedModel<EntityModel<ProdutoResponse>> pagedModel = assembler.toModel(produtos);

        return new ResponseEntity<>(pagedModel, HttpStatus.OK);
    }

    @PostMapping(produces = {"application/json", "application/xml", "application/y-yaml"},
                 consumes = {"application/json", "application/xml", "application/y-yaml"})
    public ProdutoResponse create(@RequestBody ProdutoRequest produtoRequest) {
        final var produto = this.produtoService.create(produtoRequest);
        produto.add(linkTo(methodOn(ProdutoController.class).findById(produto.getId())).withSelfRel());
        return produto;
    }

    @PutMapping(value = "/{id}",
                produces = {"application/json", "application/xml", "application/y-yaml"},
                consumes = {"application/json", "application/xml", "application/y-yaml"})
    public ProdutoResponse update(@RequestBody ProdutoRequest produtoRequest, @PathVariable("id") Long id) {
        final var produto = this.produtoService.update(produtoRequest, id);
        produto.add(linkTo(methodOn(ProdutoController.class).findById(produto.getId())).withSelfRel());
        return produto;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        this.produtoService.delete(id);
        return ResponseEntity.ok().build();
    }
}
