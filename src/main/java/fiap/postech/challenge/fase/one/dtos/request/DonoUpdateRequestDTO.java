package fiap.postech.challenge.fase.one.dtos.request;

import jakarta.validation.constraints.NotNull;

public record DonoUpdateRequestDTO(
        @NotNull(message = "O campo nome não pode ser nulo")
        String nome,
        @NotNull(message = "O campo email não pode ser nulo")
        String email,
        @NotNull(message = "O login não pode ser nulo")
        String login
) {
}
