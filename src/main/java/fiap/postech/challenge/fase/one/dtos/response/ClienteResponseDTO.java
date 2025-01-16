package fiap.postech.challenge.fase.one.dtos.response;

import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record ClienteResponseDTO(
        @NotNull
        String nome,
        @NotNull

        String email,
        @NotNull

        String endereco,
        @NotNull

        Date ultimaAlteracao
) {
}
