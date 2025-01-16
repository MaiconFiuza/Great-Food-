package fiap.postech.challenge.fase.one.dtos.request;

import jakarta.validation.constraints.NotNull;

public record UpdateSenha(
        @NotNull
        String senhaAtual,
        @NotNull(message = "O campo senha não pode ser nulo e tem o limite de  20 caracteres")
        String novaSenha
) {
}
