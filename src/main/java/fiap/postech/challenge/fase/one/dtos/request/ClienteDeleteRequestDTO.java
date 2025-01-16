package fiap.postech.challenge.fase.one.dtos.request;

import jakarta.validation.constraints.NotNull;

public record ClienteDeleteRequestDTO(
        @NotNull
        String email,
        @NotNull
        String senha
) {
}
