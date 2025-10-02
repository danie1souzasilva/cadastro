package com.funcionario.cadastro.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    private String chaveSecreta = "secreta";

    public String gerarToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 dia
                .signWith(SignatureAlgorithm.HS512, chaveSecreta)
                .compact();
    }

    public String validarToken(String token) {
        return Jwts.parser()
                .setSigningKey(chaveSecreta)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

}



