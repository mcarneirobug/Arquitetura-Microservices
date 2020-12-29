package com.mathues.pagamento.modules.venda.controller;

import com.mathues.pagamento.modules.venda.request.VendaRequest;
import com.mathues.pagamento.modules.venda.response.VendaResponse;
import com.mathues.pagamento.modules.venda.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/venda")
public class VendaController {

    private final VendaService service;

    private final PagedResourcesAssembler<VendaResponse> assembler;

    @Autowired
    public VendaController(VendaService service, PagedResourcesAssembler<VendaResponse> assembler) {
        this.service = service;
        this.assembler = assembler;
    }

    @GetMapping(value = "/{id}", produces = {"application/json", "application/xml", "application/y-yaml"})
    public VendaResponse findById(@PathVariable("id") Long id) {
        final var venda = this.service.findById(id);
        venda.add(linkTo(methodOn(VendaController.class).findById(id)).withSelfRel());
        return venda;
    }

    @GetMapping(produces = {"application/json", "application/xml", "application/y-yaml"})
    public ResponseEntity<?> findAll(@RequestParam(value = "page", defaultValue = "0") int page,
                                     @RequestParam(value = "limit", defaultValue = "12") int limit,
                                     @RequestParam(value = "direction", defaultValue = "asc") String direction) {
        var sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "data"));
        final var vendas = this.service.findAll(pageable);

        vendas.stream()
                .forEach(p -> p.add(linkTo(methodOn(VendaController.class).findById(p.getId())).withSelfRel()));

        PagedModel<EntityModel<VendaResponse>> pagedModel = assembler.toModel(vendas);

        return new ResponseEntity<>(pagedModel, HttpStatus.OK);
    }

    @PostMapping(produces = {"application/json", "application/xml", "application/y-yaml"},
            consumes = {"application/json", "application/xml", "application/y-yaml"})
    public VendaResponse create(@RequestBody VendaRequest vendaRequest) {
        final var venda = this.service.create(vendaRequest);
        venda.add(linkTo(methodOn(VendaController.class).findById(venda.getId())).withSelfRel());
        return venda;
    }
}
