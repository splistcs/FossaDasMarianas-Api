package fdmapi.comum.security;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import fdmapi.cadastro.model.Cadastro;

@Service
public class JwtService {

    private final SecretKey secretKey;
    private final long expiracao = 3600000; // 1 hora

    public JwtService(@Value("${jwt.secret}") String secret) {
        this.secretKey = Keys.hmacShaKeyFor(
            Decoders.BASE64.decode(secret)
        );
    }

    public String gerarToken(Cadastro cadastro) {

        Date agora = new Date();
        Date expiracaoToken = new Date(agora.getTime() + expiracao);

        return Jwts.builder()
            .subject(cadastro.getEmail())
            .claim("id", cadastro.getId())
            .claim("tipoCadastro", cadastro.getTipoCadastro().name())
            .issuedAt(agora)
            .expiration(expiracaoToken)
            .signWith(secretKey)
            .compact();
    }

    public long getTempoExpiracao() {
        return expiracao / 1000;
    }
}