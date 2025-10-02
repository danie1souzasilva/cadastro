package com.funcionario.cadastro.controller;

import com.funcionario.cadastro.dto.FuncDTO;
import com.funcionario.cadastro.service.FuncionarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {
    @Autowired
    private FuncionarioService funcionarioService;

    @PostMapping
    public ResponseEntity<FuncDTO> cadastrar(@RequestBody @Valid FuncDTO funcDTO){
       FuncDTO salvo = funcionarioService.cadastrar(funcDTO);
       return ResponseEntity.ok(salvo);
    }
    @GetMapping
    public ResponseEntity<List<FuncDTO>> listar() {
        List<FuncDTO> lista = funcionarioService.listarFunc();
        return ResponseEntity.ok(lista);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity <Void> deletarPorId(@PathVariable Long id){
        funcionarioService.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }

}
