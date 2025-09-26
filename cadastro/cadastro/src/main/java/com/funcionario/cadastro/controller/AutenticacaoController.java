package com.funcionario.cadastro.controller;

import com.funcionario.cadastro.entidade.Funcionario;
import com.funcionario.cadastro.repositorio.FuncionarioRepository;
import com.funcionario.cadastro.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/autenticacao")
public class AutenticacaoController {
    @Autowired
    private FuncionarioRepository funcionarioRepository;
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<String> autenticar(@RequestBody Funcionario funcionario){
        Funcionario funciEncontrado = funcionarioRepository.findByEmail(funcionario.getEmail());

        if (funciEncontrado != null && funciEncontrado.getSenha().equals(funcionario.getSenha())){
            String token = tokenService.gerarToken(funcionario.getEmail());
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais invalidas");
    }
}
