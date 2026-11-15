package fdmapi.fazerlogin.service;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import fdmapi.cadastro.model.Cadastro;
import fdmapi.cadastro.repository.CadastroRepository;
import fdmapi.comum.security.JwtService;
import fdmapi.fazerlogin.dto.FazerLoginDto;
import fdmapi.fazerlogin.dto.FazerLoginRespostaDto;

@Service
public class FazerLoginService {

    private final CadastroRepository cadastroRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public FazerLoginService(
        CadastroRepository cadastroRepository,
        PasswordEncoder passwordEncoder,
        JwtService jwtService
    ) {
        this.cadastroRepository = cadastroRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public FazerLoginRespostaDto login(FazerLoginDto loginDto) {

        // Busca o cadastro pelo e-mail
        Cadastro cadastro = cadastroRepository
            .findByEmail(loginDto.email())
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.UNAUTHORIZED,
                "E-mail ou senha incorretos."
            ));

        // Compara a senha informada com o hash BCrypt armazenado
        if (!passwordEncoder.matches(
            loginDto.senha(),
            cadastro.getSenha()
        )) {
            throw new ResponseStatusException(
                HttpStatus.UNAUTHORIZED,
                "E-mail ou senha incorretos."
            );
        }

        // Verifica se o cadastro está ativo
        if (!cadastro.isAtivo()) {
            throw new ResponseStatusException(
                HttpStatus.FORBIDDEN,
                "Cadastro ainda não foi ativado."
            );
        }

        // Gera o JWT
        String token = jwtService.gerarToken(cadastro);

        return new FazerLoginRespostaDto(
            cadastro.getId(),
            cadastro.getNomeCompleto(),
            cadastro.getEmail(),
            cadastro.getTipoCadastro().name(),
            token,
            "Bearer"
        );
    }
}
