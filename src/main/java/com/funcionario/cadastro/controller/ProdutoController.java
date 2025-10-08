package com.funcionario.cadastro.controller;

import com.funcionario.cadastro.dto.ProdutoDTO;
import com.funcionario.cadastro.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/produto")
public class ProdutoController {
    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<ProdutoDTO>cadastrar(@RequestBody @Valid ProdutoDTO produtoDTO){
        ProdutoDTO salvo = produtoService.cadastrar(produtoDTO);
        return ResponseEntity.ok(salvo);
    }
}
