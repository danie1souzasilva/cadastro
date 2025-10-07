package com.funcionario.cadastro.controller;

import com.funcionario.cadastro.dto.FornecedorDTO;
import com.funcionario.cadastro.service.FornecedorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fornecedor")
public class FornecedorController {
    @Autowired
    private FornecedorService fornecedorService;

    @PostMapping
    public ResponseEntity<FornecedorDTO> cadastrar (@RequestBody @Valid FornecedorDTO fornecedorDTO){
        FornecedorDTO salvo = fornecedorService.cadastrar(fornecedorDTO);
        return ResponseEntity.ok(salvo);
    }
    @GetMapping
    public ResponseEntity<List<FornecedorDTO>> listarFornecedores(){
        List<FornecedorDTO> lista = fornecedorService.listarFornecedores();
        return ResponseEntity.ok(lista);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Long id){
        fornecedorService.deletarFornecedor(id);
        return ResponseEntity.noContent().build();
    }
}
