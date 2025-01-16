package fiap.postech.challenge.fase.one.dtos.response;

import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record DonoResponseDTO(
        @NotNull
        Long id,
        @NotNull
        String nome,
        @NotNull
        String email,
        @NotNull
        Date ultimaAlteracao
) {
}
