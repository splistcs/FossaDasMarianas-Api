package fdmapi.fazerlogin.dto;

public record FazerLoginRespostaDto(
    Long id,
    String nomeCompleto,
    String email,
    String tipoCadastro,
    String token,
    String tipoToken
) {}