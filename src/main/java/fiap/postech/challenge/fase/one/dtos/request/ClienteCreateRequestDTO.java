package fiap.postech.challenge.fase.one.dtos.request;

import jakarta.validation.constraints.NotNull;


public record ClienteCreateRequestDTO(
        @NotNull(message = "O campo nome não pode ser nulo")
        String nome,
        @NotNull(message = "O campo email não pode ser nulo")
        String email,
        @NotNull(message = "O login não pode ser nulo")
        String login,
        @NotNull(message = "O campo senha não pode ser nulo e tem o limite de  20 caracteres")
        String senha,
        @NotNull(message = "O endereço não pode ser nulo")
        String endereco
) {
}
