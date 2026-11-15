package fdmapi.fazerlogin.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record FazerLoginDto(

    @NotBlank(message = "E-mail não informado")
    @Email(message = "E-mail inválido")
    String email,

    @NotBlank(message = "Senha não informada")
    String senha

) {
    @Override
    public String toString() {
        return "FazerLoginDto[email=" + email + ", senha=*****]";
    }
}
