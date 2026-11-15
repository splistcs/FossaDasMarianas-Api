package fdmapi.fazerlogin.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fdmapi.fazerlogin.dto.FazerLoginDto;
import fdmapi.fazerlogin.dto.FazerLoginRespostaDto;
import fdmapi.fazerlogin.service.FazerLoginService;

import jakarta.validation.Valid;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Login", description = "API para autenticação de usuários.")
@RestController
@RequestMapping("/fdm/login")
public class FazerLoginCtrl {

    private final FazerLoginService fazerLoginService;

    public FazerLoginCtrl(FazerLoginService fazerLoginService) {
        this.fazerLoginService = fazerLoginService;
    }

    @Operation(summary = "Realizar login e gerar token JWT")
    @PostMapping
    public ResponseEntity<FazerLoginRespostaDto> login(
        @Valid @RequestBody FazerLoginDto loginDto
    ) {

        FazerLoginRespostaDto resposta =
            fazerLoginService.login(loginDto);

        return ResponseEntity.ok(resposta);
    }
}
