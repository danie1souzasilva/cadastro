package com.funcionario.cadastro.controller;

import com.funcionario.cadastro.dto.ProdutoDTO;
import com.funcionario.cadastro.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping
    public ResponseEntity<List<ProdutoDTO>> listarTodos() {
        List<ProdutoDTO> lista = produtoService.listarTodos();
        return ResponseEntity.ok(lista);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        produtoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDTO> atualizarProduto(@PathVariable Long id, @RequestBody ProdutoDTO dto) {
        ProdutoDTO atualizado = produtoService.atualizar(id, dto);
        return ResponseEntity.ok(atualizado);
    }




}
